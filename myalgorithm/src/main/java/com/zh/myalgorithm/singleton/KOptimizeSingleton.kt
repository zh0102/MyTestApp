package com.zh.myalgorithm.singleton

import android.content.Context

/**
 * @ClassName: KOptimizeSingleton
 * @Description: 静态内部单列
 * @Author: ZHW
 * @Date: 2022/10/19 下午4:58
 */
class KOptimizeSingleton private constructor(context: Context) {

    fun doSomething() {
        println("doSomething.....")
    }

    companion object {

        @JvmStatic
        fun getInstance(context: Context): KOptimizeSingleton {//全局访问点
            return SingletonHold(context).mInstance
        }
    }

    private class SingletonHold(context: Context){//静态内部类
        val mInstance: KOptimizeSingleton = KOptimizeSingleton(context)
    }
}