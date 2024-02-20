package com.shilei.ijk.wandroid.ui.home

import com.shilei.ijk.wandroid.data.bean.ArticleList
import com.shilei.ijk.wandroid.data.bean.Banner

interface HomeContract {
    interface View {
        fun bindHomeData(data: ArticleList?)
        fun loadHomeDataError()
        fun noMoreData()
        fun bindBannerData(data: List<Banner?>?)
    }
}