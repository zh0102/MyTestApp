package com.zh.myalgorithm.singleton

import kotlin.contracts.ReturnsNotNull

/**
 * @ClassName: KLazilySingleton
 * @Description: 线程安全的懒汉式单例
 * @Author: ZHW
 * @Date: 2022/10/19 下午4:23
 */
class KLazilySingleton private constructor() {

    fun doSomething() {
        println("doSomething..")
    }

    companion object {
        private var mInstance: KLazilySingleton? = null
        get() {
            return field?: KLazilySingleton()
        }


        @JvmStatic
        @Synchronized //添加synchronized同步锁
        fun getInstance(): KLazilySingleton {
            return requireNotNull(mInstance)
        }
    }

}