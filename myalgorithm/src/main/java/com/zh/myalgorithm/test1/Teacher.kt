package com.zh.myalgorithm.test1

/**
 * @ClassName: Teacher
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/17 上午10:27
 */
object Teacher {

    @JvmStatic
    fun main(args: Array<String>) {
      println(name)
    }

    //姓名
    var name: String? = "张三"
        get() = field+"你好"
        set(value) {
            field = value+"张三"
        }



}