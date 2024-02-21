package com.shilei.ijk.common.ui.fragment.support

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.shilei.ijk.common.util.toShortString
import timber.log.Timber

abstract class BaseFragment : Fragment(), IFragmentSupport {
    companion object {
        private const val TAG_LIFECYCLE = "FragmentLifecycle"
        private const val TAG_KEY_EVENT = "FragmentKeyEvent"
    }

    private var isResumed = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.tag(TAG_LIFECYCLE).d("onAttach: this: $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG_LIFECYCLE).d("onCreate: this: $this")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG_LIFECYCLE).d("onStart: this: $this")
    }

    override fun onResume() {
        super.onResume()
        isResumed = true
        Timber.tag(TAG_LIFECYCLE).d("onResume: this: $this")
    }

    override fun onPause() {
        super.onPause()
        isResumed = false
        Timber.tag(TAG_LIFECYCLE).d("onPause: this: $this")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG_LIFECYCLE).d("onStop: this: $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.tag(TAG_LIFECYCLE).d("onDestroyView: this: $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG_LIFECYCLE).d("onDestroy: this: $this")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.tag(TAG_LIFECYCLE).d("onDetach: this: $this \n ------------------------------------")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Timber.tag(TAG_LIFECYCLE).d("onLowMemory: this: $this")
    }

    /** Key Event ***/
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (interceptKeyEvent(event)) {
            Timber.tag(TAG_KEY_EVENT).d("dispatchKeyEvent: $this intercept ${event.toShortString()}")
            return true
        }
        return onKeyEvent(event.keyCode, event)
    }

    final override fun interceptKeyEvent(event: KeyEvent): Boolean {
        return !isResumed || shouldInterceptKeyEvent(event)
    }

    open fun shouldInterceptKeyEvent(event: KeyEvent): Boolean {
        return false
    }

    override fun onKeyEvent(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            if ((event.flags and KeyEvent.FLAG_LONG_PRESS) != 0 && onKeyLongPress(keyCode, event)) {
                return true
            }
            return onKeyDown(keyCode, event)
        }
        if (event.action == KeyEvent.ACTION_UP) {
            return onKeyUp(keyCode, event)
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        Timber.tag(TAG_KEY_EVENT).d("onKeyDown: $this intercept ${event.toShortString()}")
        return false
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        Timber.tag(TAG_KEY_EVENT).d("onKeyUp: $this intercept ${event.toShortString()}")
        return false
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
        Timber.tag(TAG_KEY_EVENT).d("onKeyLongPress: $this intercept ${event.toShortString()}")
        return false
    }
}