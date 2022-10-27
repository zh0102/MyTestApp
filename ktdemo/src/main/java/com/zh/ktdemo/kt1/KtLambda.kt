package com.zh.ktdemo.kt1

import kotlin.math.E

/**
 * @ClassName: Ktlambda
 * @Description: java类作⽤描述
 * @Author: ZHW
 * @Date: 2022/9/16 下午4:01
 */
object KtLambda {


    @JvmStatic
    fun main(args: Array<String>) {
        val mylambda: (Int) -> Unit = { s: Int -> println(s) }
        // addNumber(2, 5, mylambda)
        // chTest()
        allTest()
    }

    fun addNumber(a: Int, b: Int, mylambda: (Int) -> Unit) {
        val add = a + b;
        mylambda(add)
    }

    fun addList() {
        val list = arrayOf(1, 2, 5)
    }

    fun chTest() {
        val str = "Hello, World!"
        val str1 = """Hell0
            |world
        """
        val str2 = "Hello, World!"
        println(str)
        println(str1)
        println(str == str2)
        println(str != str2)
        val number = getNumber("10")
        println("number=$number")
    }

    private fun getNumber(str: String): Int {
        return try {
            Integer.parseInt(str)
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun test1() {
        try {
            val data = 5 / 0
            println(data)
        } catch (e: NullPointerException) {
            println(e)
        } finally {
            println("finally block always executes")
        }
        println("below codes...")

    }

    private fun test2() {
        val obj: Any = "变量obj自动转换为此范围内的String"
        if (obj !is String) {
            // No Explicit Casting needed.
            println("obj不是字符串")
        } else {
            println("字符串的长度是：${obj.length}")
        }
    }

    private fun test3() {
        val obj: Any = "你是猪吗"
        val str: String? = obj as? String // Works
        val safeInt: Int? = obj as? Int
        println(str)
        println(safeInt)
    }

    private fun test4() {
        val num1 = 10
        val num2 = 20
        val t = if (num1 > num2)
            "彩云阁"
        else
            "来邦科技"

        println(t)
    }

    private fun test5() {
        val list: List<String> = listOf<String>("java", "android", "c++")
        var stringList: List<String> = listOf<String>("java", "android", "kotlin", "c++", "c")
        println(stringList[0])
        println(stringList.indexOf("c++"))
        println(stringList.lastIndexOf("kotlin"))
        println(stringList.size)
        println(stringList.contains("android"))
        println(stringList.containsAll(list))
        println(stringList.subList(2, 4))
        println(stringList.isEmpty())
        println(stringList.drop(1))
        println(stringList.dropLast(2))

    }

    private fun test6() {
        val mutableList = mutableListOf<String>()
        mutableList.add("java")
        mutableList.add("android")
        val mutableList2 = mutableListOf<String>("kotlin", "PHP")
        val mutableList3 = mutableListOf<String>("c++")
        val mutableList4 = mutableListOf<String>("c")
        println("-----------------开始-------------------")
        for (element in mutableList) {
            println(element)
        }
        mutableList.add(1, "PHP")
        println("mutableList.add(1,php)$mutableList")

        mutableList.addAll(1, mutableList2)
        println("mutableList.add(1,mutableList2)$mutableList")
        mutableList.addAll(3, mutableList3)
        mutableList.addAll(4, mutableList4)
        println("mutableList$mutableList")

        mutableList.removeAll(mutableList2)
        println("removeAll mutableList2:$mutableList")
        println("-----------------结束-------------------")
    }

    private fun test7() {
        val arrayList = ArrayList<String>()
        arrayList.add("java")
        arrayList.add("android")
        arrayList.add("Python")
        arrayList.add("kotlin")
        arrayList.add("c++")
        for (en in arrayList) {
            println(en)
        }
    }

    private fun test8() {
        val arrayList: ArrayList<Employee> = arrayListOf<Employee>()
        val e1 = Employee(1, "java", 188, "sz")
        val e2 = Employee(2, "android", 198, "sh")
        val e3 = Employee(3, "kotlin", 218, "bj")
        arrayList.add(e1)
        arrayList.add(e2)
        arrayList.add(e3)
        for (e in arrayList) {
            println("${e.id} ${e.name} ${e.phone} ${e.city}")
        }
    }

    private fun test9() {
        val myMap: Map<Int, String> = mapOf<Int, String>(1 to "java", 4 to "android", 6 to "kotlin")
        for (key in myMap.keys) {
            println(key.toString() + "-->" + myMap[key])
        }
        println("---myMap.getValue(4)----")
        println(myMap.getValue(4))
        println("---myMap.contains(55)----")

        println("---myMap.contains(6)----")
        println(myMap.contains(6))

        println("---myMap.containsKey(2)-")
        println(myMap.containsKey(2))

    }

    private fun allTest() {
       test9()
    }

    // 定义类
    class Employee(var id: Int, var name: String, var phone: Int, var city: String)


}