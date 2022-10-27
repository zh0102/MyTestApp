package com.zh.ktdemo.kt1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.util.rangeTo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @ClassName: MapKt
 * @Description: java类作⽤描述
 * @Author: ZHW
 * @Date: 2022/9/24 上午10:49
 */
object MapKt {

    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    fun main(args: Array<String>) {
        allTest()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun test1() {
        val myMap: Map<Int, String> = mapOf<Int, String>(1 to "java", 4 to "android", 6 to "kotlin")
        for (key in myMap.keys) {
            println(key.toString() + "-->" + myMap[key])
        }
        println("---myMap.getValue(4)----")
        println(myMap.getValue(4))

        println("---myMap.contains(6)----")
        println(myMap.contains(6))

        println("---myMap.containsKey(2)-")
        println(myMap.containsKey(2))

        println("-myMap.containsValue(kotlin)-")
        println(myMap.containsValue("kotlin"))

        println("---myMap.getOrDefault-----")
        println(myMap.getOrDefault(36, "Python"))

        println("---myMap.asIterable()-----")
        for (itr in myMap.asIterable()) {
            println("key=${itr.key} value=${itr.value}")
        }
        println("---myMap.minus(4)------")
        for (m in myMap.minus(4)) {
            println("key=${m.key} value=${m.value}")
        }

        println("......myMap.plus(Pair(5, \"Swift\"))......")
        for (p in myMap.plus(Pair(5, "Swift"))) {
            println("Element at key ${p.key} = ${p.value}")
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun test2() {
        val hashMap: HashMap<Any, String> = HashMap<Any, String>(3)
        hashMap.put(1, "java")
        hashMap.put(4, "android")
        hashMap.put(3, "kotlin")
        hashMap.put(2, "Python")
        hashMap.put("name", "张三")
        println("--hashmap.size()=${hashMap.size}")
        println("key hashmap")
        for (key in hashMap.keys) {
            println("Element at key $key = ${hashMap[key]}")
        }
        hashMap.replace(2, "C++")
        hashMap.remove("name")
        println("--replace remove-")
        for (key in hashMap.keys) {
            println("Element at key $key = ${hashMap[key]}")
        }
    }

    private fun test3() {
        val mutableMap: MutableMap<String, String> = mutableMapOf<String, String>()
        mutableMap.put("name", "张三")
        mutableMap.put("city", "深圳")
        val hashMap: HashMap<String, String> = hashMapOf<String, String>()
        hashMap.put("department", "研发部")
        hashMap.put("hobby", "撸代码")

        println("--mutableMap before---")
        for (key in mutableMap.keys) {
            println("key=${key} value=${mutableMap[key]}")
        }
        mutableMap.putAll(hashMap)
        println("mutableMap after")
        for (en in mutableMap.keys) {
            println("key=${en} value=${mutableMap[en]}")
        }

    }

    private fun test4() {
        val inset = setOf(12, 15, 12, 11, 17, 11)
        val mySet: Set<Any> = setOf(2, 6, 4, 29, 5, "Ajax", "Java")
        println("----inset----")
        for (set in inset) {
            println(set)
        }
        println("----mySet----")
        for (element in mySet) {
            println(element)
        }
        println("---mySet.isEmpty()--------")
        println(mySet.isEmpty())

        println("---mySet.isNotEmpty()--------")
        println(mySet.isNotEmpty())

        println("--mySet.drop(4)--")
        println(mySet.drop(4))
        println("--mySet.minus(4)--")
        println(mySet.minus(4))
        println("--mySet.plus(5)--")
        println(mySet.plus(2))
        println("--mySet.elementAt(3)--")
        println(mySet.elementAt(3))
        println("--mySet.elementAtOrNull(5)--")
        println(mySet.elementAtOrNull(5))
    }

    val x = 5
    var y = 5
    private fun test5() {
        fun isPotion(x: Int) = x > 0
        fun isPotion(s: String) = s == "kotlin" || s == "kotlin"
        val numbers = listOf(-10, -5, 0, 5, 10)
        val strings = listOf("kotlin", "program")

        println(numbers.filter(::isPotion))
        println(strings.filter(::isPotion))

        println(::x.get())
        println(::x.name)
        println(::y.set(10))

    }

    class NameClass {
        private var name: String = "Ashu"

        class nestedClass {
            var description: String = "code inside nested class"
            private var id: Int = 101
            fun foo() {
                //  print("name is ${name}") // cannot access the outer class member
                println("Id is ${id}")
            }
        }

        inner class innerClass {
            var descr: String = "code inner"
            fun find() {
                println("name is ${name}")
            }
        }

    }

    private fun test6() {
        println(NameClass.nestedClass().description)
        val obj = NameClass.nestedClass();
        obj.foo()
        println(NameClass().innerClass().descr)
        val inner = NameClass().innerClass();
        inner.find()
    }

    private fun test7() {
//        val hello = Hello("真的好难", 2)
//        val hello1=Hello1("光辉岁月",1234)
        val obj = Hell02.Child("海阔天空", "1234555")
        val obj2 = Hell02.Child("灰色轨迹", "12334", "深圳")

    }

    fun computeMD5(content: ByteArray?): ByteArray {
        return try {
            val md5: MessageDigest = MessageDigest.getInstance("MD5")
            md5.digest(content)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    interface MyInterface {
        var id: Int            // abstract property
        fun absMethod(): String    // abstract method
        fun doSomThing() {
            println("MyInterface1 doSomThing 工作了 ")
        }
    }

    class InterfaceImp : MyInterface {
        override var id: Int = 1000
        override fun absMethod(): String {
            return "实现抽象方法"
        }
    }


    private fun test8() {
        val obj = InterfaceImp()
        println("调用覆盖id值，当前 ID = ${obj.id}")
        obj.doSomThing()
        println(obj.absMethod())
    }

    data class User(val name: String, val age: String)

    private fun test9() {
        val user = User("张三", "19")
        println(user)
        var s: Int = 0
        for (i in 0..365) {
            s += i
        }
        println("总共需要赚：${s}")
    }

    private fun test10() {
        for (a in 1..5) {
            //println(a)
        }
        for (c in 'a'..'f') {
            // println(c)
        }
        for (b in 5 downTo 1) {
            print(b)
        }
        println()
        for (d in 1 until 5) {
            print(d)
        }
        println()
        for (x in 1.rangeTo(5))
            print(x)
        println()
        for (x in 5.downTo(1))
            print(x)
        println()
        for (x in 'a'..'e')
            print(x)
        println()
        for (x in 'e' downTo 'a')
            print(x)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun allTest() {
        test10()
    }


}