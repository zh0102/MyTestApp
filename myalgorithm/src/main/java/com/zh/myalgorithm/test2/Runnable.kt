package com.zh.myalgorithm.test2

/**
 * @ClassName: Runnable
 * @Description: 接口
 * @Author: ZHW
 * @Date: 2022/10/17 下午4:21
 */
interface Runnable {
    fun run()
}

interface Eat {
    fun eat(foot: String)

    //接口已实现，则子类可以不实现
    fun eatWith() {
        println("吃饭")
    }
}

interface Wear {
    val cloth: String
}

class Rabbit : Runnable, Eat, Wear {
    override fun run() {
        println("兔子快跑")
    }

    override fun eat(foot: String) {
        println("兔子吃$foot")
    }

    override val cloth: String
        get() = "烧鸭"

}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun main(args: Array<String>) {
    val rabbit: Rabbit = Rabbit()
    rabbit.eatWith()
    rabbit.cloth
    rabbit.eat("草")
    rabbit.run()
    println(max(5,6))
}

