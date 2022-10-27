package com.zh.ktapp.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @ClassName: WeatherDetail
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/12 下午3:15
 */

@Entity(tableName = "tb_weather_detail")
data class WeatherDetail(
    @PrimaryKey
    val time: String,//预报发布时间

    val aqi: String,//空气指数
    val rain: String,//下雨时间点
    val sd: String,//空气湿度
    val temperature: String,//气温
    val temperatureTime: String,//获取气温时间
    val weather: String,//天气
    val weatherPic: String,//天气小图标
    val windDirection: String,//风向
    val windPower: String//风力
)



