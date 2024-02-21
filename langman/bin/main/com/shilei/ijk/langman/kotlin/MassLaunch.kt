package com.shilei.ijk.langman.kotlin
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            val arrayList = ArrayList<Pair2>(10)
            val deferredList = mutableListOf<Deferred<Boolean>>()
            val random = Random(47)

            // 添加元素的协程
            for (i in 0 until 10000) {
                val deferred = async(Dispatchers.IO) {
                    val time = (random.nextFloat() * 2000L).toLong()
                    delay(time)
                    val pair = Pair2(i)
                    arrayList.add(pair)
                }
                deferredList.add(deferred)
            }

            // 等待所有添加元素的协程完成
            deferredList.awaitAll()

            // 读取 ArrayList 中的元素的协程
            val readDeferred = async(Dispatchers.IO) {
                for (pair in arrayList) {
                    println("Read element: ${pair.id}")
                }
            }

            // 等待读取操作完成
            readDeferred.await()
        }
    }
}

data class Pair2(val id: Int)
