package com.zh.myalgorithm.ipurchase;

/**
 * @ClassName: HouseAgent
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/19 下午6:19
 */
public class HouseAgent implements IPurchaseHouseKt {

    private IPurchaseHouseKt mHouseOwner;//具体房东HouseOwner被代理对象引用

    public HouseAgent(IPurchaseHouseKt houseOwner) {
        mHouseOwner = houseOwner;
    }

    @Override
    public void inquiryPrice() {
        mHouseOwner.inquiryPrice();
    }

    @Override
    public void visitHouse() {
        mHouseOwner.visitHouse();
    }

    @Override
    public void payDeposit() {
        mHouseOwner.payDeposit();
    }

    @Override
    public void signAgreement() {
        mHouseOwner.signAgreement();
    }

    @Override
    public void payMoney() {
        mHouseOwner.payMoney();
    }

    @Override
    public void getHouse() {
        mHouseOwner.getHouse();
    }
}
