package com.zh.ktdemo.kt1

/**
 * @ClassName: Hell02
 * @Description:
 * @Author: ZHW
 * @Date: 2022/9/28 上午11:37
 */
open class Hell02 {

    constructor(name: String, password: String) {
        println("第一个执行")
        println("Name = ${name}")
        println("password = ${password}")
    }

    constructor(name: String, password: String, city: String) {
        println("第三个执行")
        println("Name=${name}")
        println("Password=${password}")
        println("City=${city}")
    }

    class Child : Hell02 {

        constructor(name: String, password: String) : super(name, password) {
            println("第二个执行")
            println("Name = ${name}")
            println("Password = ${password}")
        }

        constructor(name: String, password: String, city: String) : super(name, password, city) {
            println("第四个执行")
            println("Name=${name}")
            println("Password=${password}")
            println("City=${city}")
        }
    }
}