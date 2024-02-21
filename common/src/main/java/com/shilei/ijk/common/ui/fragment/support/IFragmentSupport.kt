package com.shilei.ijk.common.ui.fragment.support

import android.view.KeyEvent


interface IFragmentSupport : IBackPressSupport {
    fun dispatchKeyEvent(event: KeyEvent): Boolean

    fun interceptKeyEvent(event: KeyEvent): Boolean

    fun onKeyEvent(keyCode: Int, event: KeyEvent): Boolean

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean = false

    fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean = false

    fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean = false
}