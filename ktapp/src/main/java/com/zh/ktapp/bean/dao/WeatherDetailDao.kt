package com.zh.ktapp.bean.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zh.ktapp.bean.WeatherDetail

/**
 * @ClassName: WeatherDetail
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/13 下午12:15
 */
@Dao
interface WeatherDetailDao {
    /**
     * 插入数据，如果冲突则替换
     * @param weatherDetail: WeatherDetail
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(weatherDetail: WeatherDetail)


    @Query("select * from tb_weather_detail")
     fun getAll(): List<WeatherDetail>
}