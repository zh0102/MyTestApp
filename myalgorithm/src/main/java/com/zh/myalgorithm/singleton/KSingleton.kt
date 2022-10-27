package com.zh.myalgorithm.singleton

import java.io.Serializable

/**
 * @ClassName: KSingleton
 * @Description: 饿汉式单例模式
 * @Author: ZHW
 * @Date: 2022/10/19 下午4:18
 */
object KSingleton : Serializable {

    fun doSomething() {
        println("doSomething.....")
    }

    private fun readResolve(): Any {
        return KSingleton
    }

}