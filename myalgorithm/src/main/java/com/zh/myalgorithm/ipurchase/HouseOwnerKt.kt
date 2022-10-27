package com.zh.myalgorithm.ipurchase

/**
 * @ClassName: HouseOwnerKt
 * @Description: 房东
 * @Author: ZHW
 * @Date: 2022/10/19 下午6:07
 */
class HouseOwnerKt :IPurchaseHouseKt{

    override fun inquiryPrice() {
       println("房东提出500万")
    }

    override fun visitHouse() {
        println("房东同意买房者来看房子")
    }

    override fun payDeposit() {
        println("房东收了买房者1W RMB定金")
    }

    override fun signAgreement() {
        println("房东与买房者签订合同")
    }

    override fun payMoney() {
        println("买房者付钱给房东")
    }

    override fun getHouse() {
        println("买房者拿到房子")
    }

}