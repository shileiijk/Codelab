package com.shilei.ijk.okretro.token

import okhttp3.Response

interface IRefreshToken {
    fun refreshToken(): Response
}