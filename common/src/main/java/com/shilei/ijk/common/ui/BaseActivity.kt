package com.shilei.ijk.common.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.shilei.ijk.common.ui.fragment.support.FragmentStackManager
import timber.log.Timber

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Timber.d("onBackPressed")
            if (FragmentStackManager.stackNotEmpty()) {
                val currentFragment = FragmentStackManager.getCurrentFragment()
                if (currentFragment?.onBackPressed() == true) {
                    return
                }
                FragmentStackManager.popFragment()
                return
            }

            if (isEnabled) {
                isEnabled = false
                Toast.makeText(this@BaseActivity, "再按一次退出", Toast.LENGTH_SHORT).show()
            }
        }
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewBinding(): VB

    open fun handleOnBackPress(): Boolean = false

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        binding = getViewBinding()
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        onActivityCreated()
    }

    open fun onActivityCreated() {
        Timber.d("onActivityCreated")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Timber.d("onKeyDown")
        return super.onKeyDown(keyCode, event)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("onConfigurationChanged")
    }

//    override fun onBackPressed() {
//        if (FragmentStackManager.stackNotEmpty()) {
//            val currentFragment = FragmentStackManager.getCurrentFragment()
//            if (currentFragment?.onBackPressed() == true) {
//                return
//            }
//            FragmentStackManager.popFragment()
//            return
//        }
//        super.onBackPressed()
//    }
}