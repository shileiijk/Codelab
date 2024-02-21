package com.shilei.ijk.onlykotlin

object Log {
    fun d(msg: String) {
        println("${Thread.currentThread()} -> " + msg)
    }
}