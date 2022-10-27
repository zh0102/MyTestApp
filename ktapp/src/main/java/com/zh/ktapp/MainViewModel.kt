package com.zh.ktapp

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.Utils
import com.zh.ktapp.base.BaseViewModel
import com.zh.ktapp.bean.Student
import com.zh.ktapp.bean.WeatherDetail
import com.zh.ktapp.bean.dao.StudentDao
import com.zh.ktapp.data.WeatherRepository
import com.zh.ktapp.room.DBManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @ClassName: MainViewModel
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/13 上午10:38
 */
class MainViewModel : BaseViewModel(){

    val weatherData: MutableLiveData<WeatherDetail> = MutableLiveData()
    val weatherDaoData: MutableLiveData<List<WeatherDetail>> = MutableLiveData()

    //获取天气数据
    fun getWeather(area: String) {
        viewModelScope.launch {
            val result = WeatherRepository.getWeather(area)
            weatherData.value = result.data.now
        }
    }

    fun getWeatherDao() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                weatherDaoData.postValue(DBManager.getInstance(Utils.getApp().applicationContext).getWeatherDetailDao().getAll())

            }
        }
    }
    override fun start() {

    }

    val studentData: MutableLiveData<List<Student>> = MutableLiveData()
    val studentDao: StudentDao =
        DBManager.getInstance(Utils.getApp().applicationContext).getStudentDao()

    fun initStudent() {
        val studentData: MutableList<Student> = mutableListOf<Student>()
        var s_1 = Student(1, "s1", "小学")
        var s_2 = Student(2, "s2", "小学")
        var s_3 = Student(3, "s3", "小学")
        var s_6 = Student(6, "s6", "大学")
        var s_5 = Student(5, "s5", "大学")
        var s_4 = Student(4, "s4", "大学")
        studentData.add(s_1)
        studentData.add(s_2)
        studentData.add(s_3)
        studentData.add(s_6)
        studentData.add(s_5)
        studentData.add(s_4)
        viewModelScope.launch {
            studentDao.insertAll(studentData)
        }
    }

    fun getStudent(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                studentData.postValue(DBManager.getInstance(Utils.getApp().applicationContext).getStudentDao().getAllStudents())
            }
        }
    }

    fun  insertStudent(){
        var s_9 = Student(19,"s4", "大学")
        viewModelScope.launch {
            studentDao.insert(s_9)
        }
    }
    fun  deleteStudent(){
        var s_9 = Student(19,"s4", "大学")
        viewModelScope.launch {
            studentDao.delete(s_9)
        }
    }
}