package com.shilei.ijk.okretro

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

abstract class BaseCallback<T> : Callback<T> {
    var isDebuggable = true

    abstract fun onSuccess(body: T?)

    abstract fun onError(th: Throwable?)

    val isCancel: Boolean
        get() = false

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (isCancel) {
            return
        }
        if (!response.isSuccessful) {
            onFailure(call, HttpException(response))
            return
        }
        onSuccess(response.body())
    }

    override fun onFailure(call: Call<T>, th: Throwable) {
        if (isCancel) {
            return
        }
        if (isDebuggable) {
            Timber.w("onFailure: ${call.request().url} + ${th.message}")
        }
        onError(th)
    }
}