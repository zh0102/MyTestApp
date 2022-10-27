package com.zh.myalgorithm.ipurchase

/**
 * @ClassName: HouseAgentKt
 * @Description:  房产中介. 注意了，重点来了，Kotlin只需要简单一行就替代了Java代理类所有样板代码
 * @Author: ZHW
 * @Date: 2022/10/19 下午6:11
 */
//通过by关键字实现代理，省略大量的代理类中的样板代码，这一点需要get
class HouseAgentKt(houseOwnerKt: IPurchaseHouseKt) : IPurchaseHouseKt by houseOwnerKt {
    private val mHouseOwnerAKt = houseOwnerKt
    var mIsSigned: Boolean = false
    override fun visitHouse() {//只需要重写visitHouse即可
        if (mIsSigned) {
            println("您已经签订了看房协议，可以看房了")
            mHouseOwnerAKt.visitHouse()
        } else {
            println("很抱歉，您还没签订了看房协议，暂时不能看房")
        }
    }
}

fun main(args: Array<String>) {
    val houseOwnerKt = HouseOwnerKt()
    HouseAgentKt(houseOwnerKt).run {
        inquiryPrice()
        visitHouse()
        payDeposit()
        signAgreement()
        payMoney()
        getHouse()
    }
    println("----------------------")
    val houseOwner: IPurchaseHouseKt = HouseOwnerKt()
    val houseAgent: IPurchaseHouseKt = HouseAgent(houseOwner)
    houseAgent.inquiryPrice()
    houseAgent.visitHouse()
    houseAgent.payDeposit()
    houseAgent.signAgreement()
    houseAgent.payMoney()
    houseAgent.getHouse()
}