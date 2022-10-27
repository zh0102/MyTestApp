package com.zh.myalgorithm.ipurchase

/**
 * @ClassName: IPurchaseHouseKt
 * @Description: 抽象买房接口
 * @Author: ZHW
 * @Date: 2022/10/19 下午6:02
 */
interface IPurchaseHouseKt {

    fun inquiryPrice()//询价
    fun visitHouse()//看房
    fun payDeposit()//付定金
    fun signAgreement()//签合同
    fun payMoney()//付钱
    fun getHouse()// 拿房子
}