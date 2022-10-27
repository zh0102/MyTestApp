package com.zh.ktapp.base

import android.util.Log
import com.zh.ktapp.BuildConfig

object LogUtils {
    private var className: String? = null//类名
    private var methodName: String? = null//方法名
    private var lineNumber = 0//行数

    /**
     * 判断是否可以调试
     * @return
     */
    val isDebuggable: Boolean
        get() =BuildConfig.DEBUG

    private fun createLog(log: String): String {
        val buffer = StringBuffer()
        buffer.append("===")
        buffer.append(methodName)
        buffer.append("(").append(className).append(":").append(lineNumber).append(")==:")
        buffer.append(log)
        return buffer.toString()
    }

    /**
     * 获取文件名、方法名、所在行数
     * @param sElements
     */
    private fun getMethodNames(sElements: Array<StackTraceElement>) {
        className = sElements[1].fileName
        methodName = sElements[1].methodName
        lineNumber = sElements[1].lineNumber
    }

    fun e(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.e(className, createLog(message))
    }

    fun i(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.i(className, createLog(message))
    }

    fun d(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.d(className, createLog(message))
    }

    fun v(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.v(className, createLog(message))
    }

    fun w(message: String) {
        if (!isDebuggable) return
        getMethodNames(Throwable().stackTrace)
        Log.w(className, createLog(message))
    }
}