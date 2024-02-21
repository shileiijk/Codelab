package com.shilei.ijk.okretro

import retrofit2.Call
import retrofit2.Response

abstract class RequestVerify<T> {
    var requestStartTime: Long = 0

    var requestEndTime: Long = 0

    var expiredIn: Long = 6 * 60 * 60 * 1000L

    open fun isExpired(): Boolean {
        return System.currentTimeMillis() - requestEndTime > expiredIn
    }

    abstract suspend fun request(): RequestResult<T>

    suspend inline fun requestCall(crossinline block: suspend () -> Call<T>): RequestResult<T> = execute2 { block() }

    suspend inline fun requestResponse(crossinline block: suspend () -> Response<T>): RequestResult<T> = execute { block() }
}