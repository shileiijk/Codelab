package com.shilei.ijk.langman.kotlin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

/**
 * 测试ArrayList多线程并发产生的空指针问题
 */
object CoroutineTest03_launchMass {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            val arrayList = mutableListOf<Pair?>()
            val deferredList = mutableListOf<Deferred<Boolean>>()
            val random = Random(47)

            for (i in 0 until 10000) {
                async(Dispatchers.IO) {
                    val time = (random.nextFloat() * 1000L).toLong()
                    println("time: $time")
                    delay(time)
                    // 模拟空指针问题：在添加元素时，可能会出现竞态条件
                    val pair = Pair(i)
                    arrayList.add(pair)
                }.run {
                    deferredList.add(this)
                }
            }

            // 等待所有协程完成
            deferredList.awaitAll()

            // 输出数组长度
            println("Array list size: ${arrayList.size}")
            arrayList.filterNotNull().sortedBy {
                it.id
            }.onEach {
                    println("index: ${it.id}")
                }
        }
    }
}

data class Pair(val id: Int)
