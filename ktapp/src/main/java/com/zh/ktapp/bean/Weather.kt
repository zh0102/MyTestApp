package com.zh.ktapp.bean


/**
 * @ClassName: Weather
 * @Description: 天气详情
 * @Author: ZHW
 * @Date: 2022/10/12 下午3:10
 */
data class Weather(
    val time: String,//预报发布时间
    val now: WeatherDetail//天气详情
)
