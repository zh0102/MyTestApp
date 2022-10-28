package com.zh.myalgorithm.test1


/**
 * @ClassName: DifferentSort
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/13 下午3:03
 */
object DifferentSort {

    @JvmStatic
    fun main(args: Array<String>) {
        test()
    }

    /**
     * 冒泡排序
     * 排序前：[2,5,8,4,1,7,9,3,6]
     * 排序后：[1,2,3,4,5,6,7,8,9]
     */
    fun bubbleSort(number: IntArray): IntArray {
        println("before=" + number.contentToString())
        for (index in 1 until number.size) {
            var flag = true
            for (i in 0 until number.size - index) {
                if (number[i] > number[i + 1]) {
                    val temp = number[i]
                    number[i] = number[i + 1]
                    number[i + 1] = temp;
                    flag = false
                }
                println("bubbleSort after==${number.contentToString()}")
            }
            println("bubbleSort==${number.contentToString()}")
            if (flag) break
        }
        return number
    }

    /**
     * 选择排序
     * 排序前：[2,5,8,4,1,7,9,3,6]
     * 排序后：[1,2,3,4,5,6,7,8,9]
     */
    fun selectSort(numArray: IntArray): IntArray {
        for (i in numArray.indices) {
            var minIndex = i;
            var j = i + 1;
            while (j in numArray.indices) {
                if (numArray[j] < numArray[minIndex]) {
                    minIndex = j
                }
                j++
            }
            if (i != minIndex) {
                val temp = numArray[i]
                numArray[i] = numArray[minIndex]
                numArray[minIndex] = temp
            }
            println("selectSort===" + numArray.contentToString() + "====" + i)

        }
        return numArray
    }

    /**
     * 插入排序
     * 排序前：[2,5,8,4,1,7,9,3,6]
     * 排序后：[1,2,3,4,5,6,7,8,9]
     */
    fun insertSort(numArray: IntArray): IntArray {
        for (i in 1 until numArray.size) {
            val tmp: Int = numArray[i]
            var j = i
            while (j > 0 && tmp < numArray[j - 1]) {
                numArray[j] = numArray[j - 1]
                j--
            }
            if (j != i) {
                numArray[j] = tmp
            }
            println("insertSort==" + numArray.contentToString() + "====" + i)
        }

        return numArray
    }

    fun test() {
        val number: IntArray = intArrayOf(2, 5, 8, 4, 1, 7, 9, 3, 6)
        insertSort(number)
    }
}