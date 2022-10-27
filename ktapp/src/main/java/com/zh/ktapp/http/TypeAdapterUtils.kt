package com.zh.ktapp.http

import android.util.Log
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.lang.NumberFormatException

/**
 * @ClassName: TypeAdapterUtils
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/13 上午11:26
 */
object TypeAdapterUtils {
    /**
     *对java.lang.NumberFormatException: empty String 的处理
     */
    fun intTypeAdapter() =  object : TypeAdapter<Number>(){
        override fun write(out: JsonWriter?, value: Number?) {
            out?.value(value)
        }

        override fun read(inJsonReader: JsonReader?): Number {
            if (inJsonReader == null){
                return 0
            }
            if (inJsonReader.peek() === JsonToken.NULL) {
                inJsonReader.nextNull()
                return 0
            }
            return try {
                val result: String? = inJsonReader.nextString()
                if (result.isNullOrEmpty()) 0 else result.toInt()
            } catch (e: NumberFormatException) {
                Log.d("intTypeAdapter", "read:${JsonSyntaxException(e)} ")
            }
        }


    }
}