package com.shilei.ijk.common.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.shilei.ijk.common.R
import timber.log.Timber

class GlobalDialog(context: Context) : AlertDialog(context) {

    init {
        setContentView(R.layout.dialog_common)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
    }

    override fun setView(view: View?) {
        super.setView(view)
        Timber.d("setView")
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        Timber.d("setContentView")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Timber.d("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Timber.d("onDetachedFromWindow")
    }
}