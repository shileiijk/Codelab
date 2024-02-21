package com.shilei.ijk.okretro.token

import com.shilei.ijk.okretro.anno.AuthToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import timber.log.Timber
import java.util.concurrent.CountDownLatch

/**
 *     @AuthToken(needAuthToken = false)
 *     @POST("/user/register")
 *     @FormUrlEncoded
 *     fun registerAction(
 *         @Field("username") username: String,
 *         @Field("password") password: String,
 *         @Field("repassword") repassword: String
 *     ): Observable<BaseResponse<RegisterResponse>> // 返回值
 */
class AuthTokenStrategyV2() : IAuthTokenStrategy() {
    private val refreshTokenListeners = ArrayList<RefreshTokenListener>()

    @Volatile
    private var isRefreshing = false

    override fun authenticate(chain: Interceptor.Chain) {
        val request = chain.request()
        if (isNeedAuthToken(request)) {
            Timber.d("authenticate: need refresh token")
            tryRefreshToken()
        } else {
            Timber.d("authenticate: no need to refresh token, do nothing")
        }
    }

    /**
     * 判断接口是否添加了AuthToken注解
     * 接口默认需要验证token
     */
    private fun isNeedAuthToken(request: Request): Boolean {
        val invocation = request.tag(Invocation::class.java)
        val method = invocation?.method()
        val authToken: AuthToken? = method?.annotations?.find { it is AuthToken } as AuthToken?
        return authToken?.needAuthToken ?: true
    }

    private fun tryRefreshToken() {
        Timber.d("tryRefreshToken: isRefreshing: $isRefreshing")
        if (isRefreshing) {
            waitRefreshing()
        } else {
            isRefreshing = true
            refreshToken()
        }
    }

    private fun waitRefreshing() {
        Timber.d("waitRefreshing: new count down latch")
        val countDownLatch = CountDownLatch(1)
        refreshTokenListeners.add(object : RefreshTokenListener {
            override fun onRefresh(isSuccess: Boolean) {
                Timber.d("waitRefreshing: onRefresh: isSuccess: $isSuccess ")
                if (isSuccess) {

                } else {
                    // do nothing
                }
                refreshTokenListeners.remove(this)
                countDownLatch.countDown() // 成功后继续请求
            }
        })

        try {
            Timber.d("waitRefreshing: wait start")
            countDownLatch.await()
            Timber.d("waitRefreshing: wait end")
        } catch (ex: InterruptedException) {
            Timber.d("waitRefreshing: interrupted")
        }
    }

    private fun refreshToken() {
        // TODO: 请求接口刷新token
        val response = Response.Builder().build()
        if (response.isSuccessful) {
            Timber.d("refreshToken: success")
            // TODO: 更新Token
        } else {
            Timber.d("refreshToken: failure")
        }
    }

    /**
     * 将RefreshToken结果通知到所有队列中等待的线程
     */
    private fun triggerRefreshListener(isSuccess: Boolean) {
        refreshTokenListeners.onEach { it.onRefresh(isSuccess) }
    }

    interface RefreshTokenListener {
        fun onRefresh(isSuccess: Boolean)
    }
}