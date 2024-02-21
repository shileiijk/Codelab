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
        const val TAG_APP_LIFECYCLE = "ApplicationLifecycle"
        const val TAG_ACTIVITY_LIFECYCLE = "ActivityLifecycle"
        lateinit var appContext: Context
    }

    private var topActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Timber.plant(DebugLogTree())
        Timber.tag(TAG_APP_LIFECYCLE).d("onCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.tag(TAG_APP_LIFECYCLE).d("onConfigurationChanged")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.tag(TAG_APP_LIFECYCLE).d("onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Timber.tag(TAG_APP_LIFECYCLE).d("onTrimMemory")
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.tag(TAG_APP_LIFECYCLE).d("onTerminate")
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityCreated: ${activity::class.java.simpleName}")
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityStarted: ${activity::class.java.simpleName}")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityResumed: ${activity::class.java.simpleName}")
        topActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityPaused: ${activity::class.java.simpleName}")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityStopped: ${activity::class.java.simpleName}")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivitySaveInstanceState: ${activity::class.java.simpleName}")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.tag(TAG_ACTIVITY_LIFECYCLE).d("onActivityDestroyed: ${activity::class.java.simpleName}")
    }
}