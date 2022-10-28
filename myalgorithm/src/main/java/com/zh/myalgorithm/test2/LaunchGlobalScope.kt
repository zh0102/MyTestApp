package com.zh.myalgorithm.test2

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @ClassName: LaunGlobalScope
 * @Description: 携程
 * @Author: ZHW
 * @Date: 2022/10/28 下午12:02
 */
object LaunchGlobalScope {

    @JvmStatic
    fun main(args: Array<String>) {

    }

    private suspend fun test1() {
        val job= GlobalScope.launch(start = CoroutineStart.ATOMIC) {

        }
        job.cancel()
        job.join()
    }

}