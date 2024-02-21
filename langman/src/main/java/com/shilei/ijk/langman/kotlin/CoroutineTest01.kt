package com.shilei.ijk.langman.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CoroutineTest01 {
    @JvmStatic
    fun main(args: Array<String>) {
        Log.d("Start")

        runBlocking {
            launch {
                delay(1000)
                Log.d("Coroutine 1")
            }

            launch {
                delay(2000)
                Log.d("Coroutine 2")
            }
        }

        Log.d("End")
    }
}