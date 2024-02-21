package com.shilei.ijk.okretro.token

import okhttp3.Interceptor

abstract class IAuthTokenStrategy() {
    abstract fun authenticate(chain: Interceptor.Chain)
}