package com.zh.ktapp.mqtt

import java.lang.reflect.Method

/**
 * @ClassName: Utils
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/21 上午10:39
 */
object Tools {

    fun getDeviceSn(): String? {
        var serial: String? = null
        try {
            val c = Class.forName("android.os.SystemProperties")
            val get: Method = c.getMethod("get", String::class.java)
            serial = get.invoke(c, "ro.serialno") as String?
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return serial
    }

}