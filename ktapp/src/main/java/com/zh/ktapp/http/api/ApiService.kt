package com.zh.ktapp.http.api

import com.zh.ktapp.http.CResponse
import com.zh.ktapp.bean.Weather
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @ClassName: ApiService
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/13 上午11:46
 */
interface ApiService {
    @FormUrlEncoded
    @POST("/9-2")
    fun getWeather(@Field("area") area: String): CResponse<Weather>
}