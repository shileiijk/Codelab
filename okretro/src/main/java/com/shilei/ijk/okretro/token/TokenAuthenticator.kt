package com.shilei.ijk.okretro.token

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.HttpURLConnection

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val code = response.code

        // 账号未登录或Token过期，刷新Token或跳转登录页
        if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // TODO: Token 过期，刷新token
        }
        return response.request
    }
}