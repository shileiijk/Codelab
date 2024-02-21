package com.shilei.ijk.common

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import com.shilei.ijk.common.log.DebugLogTree
import timber.log.Timber

open class BaseApplication : Application(), ActivityLifecycleCallbacks {

    companion object {
        var appContext: Context? = null
    }


    var topActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        appContext = this
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

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.d("onActivityCreated: $activity")
        topActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.d("onActivityDestroyed: $activity")
        if (topActivity == activity) {
            topActivity = null
        }
    }
}