package com.shilei.ijk.wandroid

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class CoroutineTest {
    fun main()
    {
        runBlocking {
            val promptChlList = listOf("CCTV1", "CCTV2", "CCTV3", "CCTV4", "CCTV5")
            val request = List<Deferred<*>>(promptChlList.size) {
                async(Dispatchers.IO) {
//                    tclChannelApis.getEPG(
//                        startTime = startString,
//                        endTime = endTimeString
//                    ).execute()
                }
            }
            request.awaitAll().apply {

            }
            request.forEach {
                it.await()
            }
        }

    }

}