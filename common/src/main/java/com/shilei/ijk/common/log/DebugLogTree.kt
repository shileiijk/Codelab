package com.shilei.ijk.common.log

import android.util.Log
import timber.log.Timber

class DebugLogTree : Timber.DebugTree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        if (isDebuggable) {
            return true
        }
        return priority > Log.DEBUG
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, TAG + tag, message, t)
    }

    companion object {
        private const val TAG = "Lab+: "
        private const val isDebuggable = true
    }
}