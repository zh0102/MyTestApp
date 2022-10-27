package com.zh.ktapp.mqtt

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.blankj.utilcode.util.Utils
import java.lang.reflect.Method

/**
 * @ClassName: Utils
 * @Description: TODO
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