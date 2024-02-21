package com.shilei.ijk.langman.kotlin

object ForEachTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        testContinue()
//        testBreak()
//        testBreak2()
        testListInsert()
    }

    private fun testListInsert() {
        println("testListInsert: foreach start ---")
        val list: MutableList<String> = mutableListOf("1", "2", "3")
        list.add(1, "5")
        list.forEach {
            print("it: $it, ")
        }
        println("testListInsert: foreach end")
    }

    /**
     * 模拟Java中的continue标签
     */
    private fun testContinue() {
        println("testContinue: foreach start ---")
        val list: List<String> = mutableListOf("1", "2", "3")
        list.forEach {
            if (it == "2") {
                return@forEach
            }
            println("testContinue: current is: $it")
        }
        println("testContinue: foreach end")
    }

    /**
     * 模拟Java中的break标签
     */
    private fun testBreak() {
        println("testBreak: foreach start ---")
        val list: List<String> = mutableListOf("1", "2", "3")
        run {
            list.forEach {
                if (it == "2") {
                    return@run
                }
                println("testBreak: current is: $it")
            }
        }
        println("testBreak: foreach end")
    }

    /**
     * 模拟Java中的break标签
     */
    private fun testBreak2() {
        println("testBreak2: foreach start ---")
        val list: List<String> = mutableListOf("1", "2", "3")
        list.reversed()
        list.takeWhile {
            it < "2"
        }.forEach {
            println("testBreak2: current is: $it")
        }
        println("testBreak2: foreach end")
    }
}