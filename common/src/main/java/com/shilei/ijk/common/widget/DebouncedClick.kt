package com.shilei.ijk.common.widget

import android.view.View

/**
 * 屏蔽连续点击事件
 */
class DebouncedClick(
    private val interval: Long = 300L,
    private val callback: ((view: View) -> Unit)? = null
) : View.OnClickListener {

    private var lastClickTime = 0L

    override fun onClick(view: View) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= interval) {
            lastClickTime = currentTime
            callback?.invoke(view)
        }
    }
}