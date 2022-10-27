package com.zh.ktapp.http

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @ClassName: RetrofitManager
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/12 下午2:22
 */
object RetrofitManager {

    /**
     * okhttpClient
     */
    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            //  .addInterceptor(CommonInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .build()


    /**
     * 构建service
     */
    fun <T> getRetrofit(url: String, serviceClass: Class<T>): T {
        val gson = GsonBuilder().serializeNulls().disableHtmlEscaping()
            .registerTypeAdapter(Int::class.java, TypeAdapterUtils.intTypeAdapter())
            .create()
        val retrofit = Retrofit.Builder().apply {
            baseUrl(url)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))//Gson转换工厂
            addConverterFactory(ScalarsConverterFactory.create())//String转换工厂
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()
        return retrofit.create(serviceClass)
    }

}