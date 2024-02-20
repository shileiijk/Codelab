package com.shilei.ijk.wandroid.api

import com.shilei.ijk.okretro.BaseResponse
import com.shilei.ijk.okretro.DataCallback
import com.shilei.ijk.wandroid.data.bean.ArticleList
import com.shilei.ijk.wandroid.data.bean.Banner
import retrofit2.Call
import timber.log.Timber

class WanApiClient {
    companion object {
        private var apiClient: WanApiClient? = null
        private lateinit var apiService: WandroidService
        val instance: WanApiClient?
            get() {
                if (apiClient == null) {
                    apiClient = WanApiClient()
                }
                return apiClient
            }
    }

    init {
        apiService = ServiceGenerator.createService(WandroidService::class.java)
    }

    private fun <T> enqueue(call: Call<BaseResponse<T>>, callback: DataCallback<T>?) {
        if (callback == null) {
            Timber.e("call: $call, callback is null")
            return
        }
        call.enqueue(callback)
    }

    fun listHomeArticles(page: Int, callback: DataCallback<ArticleList>?) {
        enqueue(apiService.listHomeArticles(page), callback)
    }

    fun requestBanner(callback: DataCallback<List<Banner>>?) {
        enqueue(apiService.requestBanner(), callback)
    }
}