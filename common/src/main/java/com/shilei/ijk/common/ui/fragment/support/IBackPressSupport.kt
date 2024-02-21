package com.shilei.ijk.common.ui.fragment.support

interface IBackPressSupport {
    /**
     * @return true 表示Back事件被消费
     */
    fun onBackPressed(): Boolean {
        return false
    }
}