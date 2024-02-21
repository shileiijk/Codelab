package com.shilei.ijk.common.widget

import android.view.View

/**
 * 连续点击判定
 */
class ConsecutiveClick(
    private val threshold: Int = 5,
    private val timeInterval: Long = 500L,
    private val clickCb: ((view: View) -> Unit)?
) : View.OnClickListener {
    private var clickCounter = 0
    private var lastClickTime = 0L

    override fun onClick(view: View) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < timeInterval) {
            clickCounter++
        } else {
            clickCounter = 1
        }
        lastClickTime = currentTime

        if (clickCounter == threshold) {
            clickCb?.invoke(view)
        }
    }
}