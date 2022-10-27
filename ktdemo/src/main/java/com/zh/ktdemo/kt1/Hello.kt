package com.zh.ktdemo.kt1

import java.util.*

/**
 * @ClassName: Hello
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/9/28 上午9:54
 */
class Hello   constructor(name: String, id: Int) {
    var e_name: String? = null
    var e_id: Int = 0


    init {
        e_name = name.capitalize(Locale.ROOT)
        e_id = id
        println("e_name=${e_name} e_id=${e_id}")
    }

}