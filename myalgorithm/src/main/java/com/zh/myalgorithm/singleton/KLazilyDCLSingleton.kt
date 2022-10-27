package com.zh.myalgorithm.singleton

/**
 * @ClassName: KLazilyDCLSingleton
 * @Description: TODO
 * @Author: ZHW
 * @Date: 2022/10/19 下午4:35
 */
class KLazilyDCLSingleton private constructor() {

    fun doSomething() {
        println("doSomething....")
    }

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val mInstance: KLazilyDCLSingleton by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            KLazilyDCLSingleton()
        }
    }
}