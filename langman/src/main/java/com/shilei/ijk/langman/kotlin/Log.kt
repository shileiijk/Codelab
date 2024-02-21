package com.shilei.ijk.langman.kotlin

object Log {
    fun d(msg: String) {
        println("${Thread.currentThread()} -> " + msg)
    }
}