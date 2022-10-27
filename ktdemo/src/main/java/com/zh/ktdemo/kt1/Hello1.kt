package com.zh.ktdemo.kt1

/**
 * @ClassName: Hello1
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/9/28 上午11:17
 */
class Hello1 {

//    constructor(e_name: String, e_password: String) : this(e_name){
//        println("name=${e_name}")
//        println("password=${e_password}")
//    }

    constructor(name: String, id: Int) : this(name, id, "mypassword") {
        println("第二个执行")
        println("Name = ${name}")
        println("Id = ${id}")
    }

    constructor(name: String, id: Int, pass: String) {
        println("第一个执行")
        println("Name = ${name}")
        println("Id = ${id}")
        println("Password = ${pass}")
    }

}