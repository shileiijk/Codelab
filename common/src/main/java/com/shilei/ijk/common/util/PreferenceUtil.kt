package com.shilei.ijk.common.util

import android.content.Context
import android.content.SharedPreferences
import com.shilei.ijk.common.BaseApplication

object PreferenceUtil {
    private val preference: SharedPreferences

    init {
        val context = BaseApplication.appContext
        preference = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preference.getBoolean(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String, defValue: String): String {
        return preference.getString(key, defValue) ?: defValue
    }

    fun putString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return preference.getInt(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        preference.edit().putInt(key, value).apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        return preference.getLong(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        preference.edit().putLong(key, value).apply()
    }
}