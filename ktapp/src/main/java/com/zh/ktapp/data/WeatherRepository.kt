package com.zh.ktapp.data

import com.zh.ktapp.http.CResponse
import com.zh.ktapp.bean.Weather
import com.zh.ktapp.http.HttpConstant
import com.zh.ktapp.http.RetrofitManager
import com.zh.ktapp.http.api.ApiService

/**
 * @ClassName: WeatherRepository
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/12 下午3:01
 */
object WeatherRepository {

    private val service by lazy {
        RetrofitManager.getRetrofit(
            HttpConstant.BASE_URL, ApiService::class.java
        )
    }

     fun getWeather(area: String): CResponse<Weather> {
        return service.getWeather(area)
    }

}