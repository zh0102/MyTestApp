package com.zh.ktapp.data

import com.blankj.utilcode.util.Utils
import com.zh.ktapp.http.CResponse
import com.zh.ktapp.bean.Weather
import com.zh.ktapp.http.HttpConstant
import com.zh.ktapp.http.RetrofitManager
import com.zh.ktapp.http.api.ApiService
import com.zh.ktapp.room.DBManager

/**
 * @ClassName: WeatherRepository
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/12 下午3:01
 */
object WeatherRepository {

    private val service by lazy {
        RetrofitManager.getRetrofit(
            HttpConstant.BASE_URL, ApiService::class.java
        )
    }

    suspend fun getWeather(area: String): CResponse<Weather> {
        return service.getWeather(area)
    }

}