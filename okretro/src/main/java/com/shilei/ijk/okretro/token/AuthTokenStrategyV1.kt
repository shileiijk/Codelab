package com.shilei.ijk.okretro.token

import com.shilei.ijk.okretro.anno.AuthTokenHead
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 *     @Headers("$AUTH_TOKEN: ${Bool.FALSE}")
 *     @POST("/user/register")
 *     @FormUrlEncoded
 *     fun registerAction(
 *         @Field("username") username: String,
 *         @Field("password") password: String,
 *         @Field("repassword") repassword: String
 *     ): Observable<BaseResponse<RegisterResponse>> // 返回值
 */
class AuthTokenStrategyV1() : IAuthTokenStrategy() {
    override fun authenticate(chain: Interceptor.Chain) {
        val request = chain.request()
        val isNeedAuthToken = request.header(AuthTokenHead.AUTH_TOKEN)
        if (isNeedAuthToken != null) {
            request.newBuilder().removeHeader(AuthTokenHead.AUTH_TOKEN)
            if (AuthTokenHead.FALSE == isNeedAuthToken) {
                Timber.d("authenticate: no need authenticate token")
            } else {
                refreshToken()
            }
        } else {
            // 接口请求默认需要token
            refreshToken()
        }
    }

    private fun refreshToken() {
        val response = Response.Builder().build()
        if (response.isSuccessful) {
            Timber.d("refreshToken: success")
            // TODO: 更新Token
        } else {
            Timber.d("refreshToken: failure")
        }
    }
}