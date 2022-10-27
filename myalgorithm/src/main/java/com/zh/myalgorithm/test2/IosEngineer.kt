package com.zh.myalgorithm.test2

/**
 * @ClassName: IosEngineer
 * @Description: 内部类
 * @Author: ZHW
 * @Date: 2022/10/17 下午3:16
 */
class IosEngineer {
    private val name:String="Android工程师"

     inner class AppleComputer(var softerName:String){
        //标记为 inner 的嵌套类能够访问其外部类的成员
        fun doWork()= println("$name 使用$softerName 工作")
    }


    interface OnItemClickListener{
        fun onclick()
    }

    class Button{
        private var onItemClickListenerA:OnItemClickListener?=null
        fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
            onItemClickListenerA=onItemClickListener
        }
    }
    fun test(){
        val button=Button()
        button.setOnItemClickListener(object:OnItemClickListener{
            override fun onclick() {
               println("点击了按钮")
            }
        })
    }
}