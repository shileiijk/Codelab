package com.tcl.tv.tclchannel.network.okretro

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class LifecycleScope(
    lifecycleOwner: LifecycleOwner,
    override val coroutineContext: CoroutineContext
) : CoroutineScope, LifecycleEventObserver {
    val lifecycle: Lifecycle = lifecycleOwner.lifecycle

    private val globalExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    init {
        lifecycle.addObserver(this)
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            coroutineContext.cancel()
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                lifecycle.removeObserver(this)
                coroutineContext.cancel()
            }

            else -> {}
        }
    }
}