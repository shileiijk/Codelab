package com.shilei.ijk.common

import android.app.Application
import android.content.res.Configuration
import com.shilei.ijk.common.log.DebugLogTree
import timber.log.Timber

open class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugLogTree())
        Timber.d("onCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("onConfigurationChanged")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.d("onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Timber.d("onTrimMemory")
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.d("onTerminate")
    }
}