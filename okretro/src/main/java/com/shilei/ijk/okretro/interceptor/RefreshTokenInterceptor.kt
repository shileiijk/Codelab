package com.shilei.ijk.okretro.interceptor

import com.shilei.ijk.okretro.token.AuthTokenStrategyV2
import com.shilei.ijk.okretro.token.IAuthTokenStrategy
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.net.HttpURLConnection

class RefreshTokenInterceptor(
    private val authTokenStrategy: IAuthTokenStrategy = AuthTokenStrategyV2()
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val tokenExpired = false
        if (tokenExpired) {
            authTokenStrategy.authenticate(chain)
        } else {
            Timber.d("intercept: token normal, request directly")
        }

        val response = chain.proceed(request)
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            authTokenStrategy.authenticate(chain)
            return chain.proceed(request)
        } else {
            return response
        }
    }
}