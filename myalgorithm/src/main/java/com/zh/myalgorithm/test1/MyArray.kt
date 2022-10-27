package com.zh.myalgorithm.test1

/**
 * @ClassName: MyArray
 * @Description: 数组相关
 * @Author: ZHW
 * @Date: 2022/10/13 下午4:12
 */
object MyArray {


    /**
     * 给你一个整数数组nums ，请计算数组的 中心下标 。

    数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。

    如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。

    如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。

    示例 1：

    输入：nums = [1, 7, 3, 6, 5, 6]
    输出：3
    解释：
    中心下标是 3 。
    左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
    右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
    示例 2：

    输入：nums = [1, 2, 3]
    输出：-1
    解释：
    数组中不存在满足此条件的中心下标。
    示例 3：

    输入：nums = [2, 1, -1]
    输出：0
    解释：
    中心下标是 0 。
    左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
    右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
     */

    fun pivotIndex(nums: IntArray): Int {
        var leftTotal: Int
        var rightTotal: Int
        var index = -1
        for (result in nums.indices) {
            println("result=" + result)
            leftTotal = 0
            rightTotal = 0
            for (left in 0 until result) {
                leftTotal += nums[left]
                println("leftTotal=$leftTotal=left=$left")
            }

            for (right in result + 1 until nums.size) {
                rightTotal += nums[right]
                println("rightTotal=$rightTotal=right=$right")
            }
            if (leftTotal == rightTotal) {
                index = result
                break
            }
        }
        return index

    }

    fun test() {
        var nums = intArrayOf(1, 7, 3, 6, 5, 6)
        var nums2 = intArrayOf(1, 2, 3)
        var nums3 = intArrayOf(2, 1, -1)
        // println("test1=" + pivotIndex(nums))
        //println("test2=" + pivotIndex(nums2))
        println("test3=" + pivotIndex(nums3))
    }

    /**
     * 1672. 最富有客户的资产总量
     *
     * 题目：给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。

    客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。

    示例 1：
    输入：accounts = [[1,2,3],[3,2,1]]
    输出：6
    解释：
    第 1 位客户的资产总量 = 1 + 2 + 3 = 6
    第 2 位客户的资产总量 = 3 + 2 + 1 = 6
    两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。

    示例 2：
    输入：accounts = [[1,5],[7,3],[3,5]]
    输出：10
    解释：
    第 1 位客户的资产总量 = 6
    第 2 位客户的资产总量 = 10
    第 3 位客户的资产总量 = 8
    第 2 位客户是最富有的，资产总量是 10

    示例 3：
    输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
    输出：17

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/richest-customer-wealth
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var max = 0;
        if (accounts.isNotEmpty()) {
            var m = accounts.size
            for (i in 0 until m) {
                var inAccount: IntArray = accounts[i]
                var n = inAccount.size
                var add = 0;
                for (j in 0 until n) {
                    add += inAccount[j]
                }
                println("add=$add-max=$max")
                if (add > max) {
                    max = add
                }
            }
        }

        return max
    }

    fun test2() {
        val x = intArrayOf(2, 8, 7)
        val y = intArrayOf(7, 1, 3)
        val z = intArrayOf(1, 9, 5)
        val accounts: Array<IntArray> = arrayOf(x, y, z)
        println("max=" + maximumWealth(accounts).toString())
    }

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

    你可以按任意顺序返回答案。

    示例 1：

    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    示例 2：

    输入：nums = [3,2,4], target = 6
    输出：[1,2]
    示例 3：

    输入：nums = [3,3], target = 6
    输出：[0,1]

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/two-sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var result = intArrayOf()
        nums.forEachIndexed { index, num ->
            println("index=$index+--num=$num")
            for (i in index + 1 until nums.size) {
                if (num + nums[i] == target) {
                    result = intArrayOf(index, i)
                }
            }
        }
        return result
    }

    fun test3() {
        val args = intArrayOf(3, 2, 4)
        println(twoSum(args, 6).contentToString())
    }

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    例如，121 是回文，而 123 不是。

    示例 1：

    输入：x = 121
    输出：true
    示例2：

    输入：x = -121
    输出：false
    解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3：

    输入：x = 10
    输出：false
    解释：从右向左读, 为 01 。因此它不是一个回文数。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/palindrome-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    open fun isPalindrome(x: Int): Boolean {
        var x = x
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false
        }
        var ans = 0
        val old = x
        while (x > 0) {
            ans = ans * 10 + x % 10
            x /= 10
        }
        return ans == old
    }

    /**
     *给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。

    返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。

    两个下标i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。

    示例 1：

    输入：s = "loveleetcode", c = "e"
    输出：[3,2,1,0,1,0,0,1,2,2,1,0]
    解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
    距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
    距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
    对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
    距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
    示例 2：

    输入：s = "aaab", c = "b"
    输出：[3,2,1,0]

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shortest-distance-to-a-character
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun shortestToChar(s: String, c: Char): IntArray {
        val n = s.length
        val res = IntArray(n)
        run {
            var i = 0
            var index = -n
            s.forEachIndexed { postion, char ->
                if (s[i] == c) index = i
                res[i] = i - index
                i++
            }
//            while (i < n) {
//                if (s[i] == c) index = i
//                res[i] = i - index
//                i++
//            }
        }
        var i = n - 1
        var index = 2 * n
        while (i >= 0) {
            if (s[i] == c) index = i
            res[i] = Math.min(index - i, res[i])
            i--
        }
        return res
    }

    /**
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。

    示例 1：

    输入：nums = [1,2,3,1]
    输出：true
    示例 2：

    输入：nums = [1,2,3,4]
    输出：false
    示例3：

    输入：nums = [1,1,1,3,3,4,3,2,4,2]
    输出：true

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/contains-duplicate
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun containsDuplicate(nums: IntArray): Boolean {
        if (nums.isNotEmpty()) {
            var size = 0
            var nowNum: Int
            for (index in nums.indices) {
                nowNum = nums[index]
                for (nextIndex in index + 1 until nums.size) {
                    if (nowNum == nums[nextIndex]) {
                        size++
                        break
                    }
                }
                if (size > 0) {
                    break
                }
            }
            return size > 0
        }
        return false
    }

    fun test6() {
        val args = intArrayOf(1, 2, 3, 4, 5, 4)
        val args2 = intArrayOf(1, 2, 3, 4, 5, 6)
        println(containsDuplicate(args))
        println(containsDuplicate(args2))
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    子数组 是数组中的一个连续部分。

    示例 1：

    输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出：6
    解释：连续子数组[4,-1,2,1] 的和最大，为6 。
    示例 2：

    输入：nums = [1]
    输出：1
    示例 3：

    输入：nums = [5,4,-1,7,8]
    输出：23

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/maximum-subarray
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun maxSubArray(nums: IntArray): Int {
        var sum = nums[0]
        var n = nums[0]
        for (i in 1 until nums.size) {
            if (n > 0) {
                n += nums[i]
            } else {
                n = nums[i]
            }
            if (sum < n) {
                sum = n
            }
        }
        return sum
    }

    fun test7() {
        val nums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        println(maxSubArray(nums))
    }

    /**
     * 基本二分查找
     * numArray 数组
     * target 目标值
     *
     * @return 目标值的位置，没有匹配到则为-1
     */
    fun binaryForTarget(numArray: IntArray, target: Int): Int {
        if (numArray.isEmpty()) {
            return -1
        }
        //1.定义左右边界L、R,确定搜索范围
        var left = 0
        var right = numArray.size - 1
        while (left <= right) {
            //3.获取中间索引
            var mid = left + (right - left) / 2//防止溢出
            when {
                //如果查找的值等于查找目标值就直接返回中间索引
                numArray[mid] == target -> {
                    return mid
                }
                //如果目标值小于中间值,那中间值的右侧都比目标值大无须比较，Mid-1设为右边界，重新查找
                numArray[mid] > target -> {
                    right = mid - 1
                }
                //如果目标值大于中间值,那中间值的左侧都比目标值小无须比较，Mid+1设为右边界，重新查找
                numArray[mid] < target -> {
                    left = mid + 1
                }
            }
        }
        return -1
    }
    fun test8() {
        val nums = intArrayOf(1, 2, 12, 23, 32, 35, 42, 43, 56, 61, 76)
        println(binaryForTarget(nums,35))
    }
    @JvmStatic
    fun main(args: Array<String>) {
        test8()
    }
}