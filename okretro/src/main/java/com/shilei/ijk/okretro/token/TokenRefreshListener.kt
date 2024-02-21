package com.shilei.ijk.okretro.token

interface TokenRefreshListener {
    fun onTokenRefreshFailed()
    fun onTokenRefreshCancelled()
}