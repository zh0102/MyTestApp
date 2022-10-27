package com.zh.ktdemo.kt1;

/**
 * @ClassName: test
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/10 下午5:40
 */
public class SuTest {
    public static void main(String[] args) {

        System.out.println("调试："+testMethod3(365));
    }

    private static int testMethod3(int n) {
        if (n <= 1) {
            return n;
        }
        return n + testMethod3(n - 1);
    }

}
