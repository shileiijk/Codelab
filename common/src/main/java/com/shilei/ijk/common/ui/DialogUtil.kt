package com.shilei.ijk.common.ui

import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.shilei.ijk.common.BaseApplication
import com.shilei.ijk.common.R

class DialogUtil {
    fun show() {
        BaseApplication.appContext?.let {
            AlertDialog.Builder(it)
                .setView(R.layout.dialog_common)
                .setTitle("This is title")
                .setOnDismissListener { _ ->
//                    isRefreshing = false
                }.setCancelable(false)
                .create()
                .apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    findViewById<TextView>(R.id.message)?.setText("请示失败，请重试")
                    findViewById<View>(R.id.btn_okay)?.apply {
                        requestFocus()
                    }?.setOnClickListener {
//                        tryRefreshToken()
                        dismiss()
                    }
                    findViewById<View>(R.id.btn_cancel)?.setOnClickListener {
                        dismiss()
                    }
                    window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                }.show()
        }
    }
}