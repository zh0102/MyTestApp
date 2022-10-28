package com.zh.myalgorithm.test2

/**
 * @ClassName: Computer
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/17 上午11:31
 */
object Computer {
    @JvmStatic
    fun main(args: Array<String>) {
         val computer:Computer=WindowComputer()
        computer.runSoftware()
        computer.withMouse("你值得拥有")
        computer.withKeyBoard("不想要")
    }

    abstract class Computer(val name:String){
        abstract fun runSoftware()

        fun withMouse(mouseName:String){
            print("$name 配置 $mouseName 鼠标 ")
        }
        open fun withKeyBoard(KeyBoardName:String){
            print("$name 配置 $KeyBoardName 键盘")
        }
    }
    class WindowComputer:Computer("Linux电脑"){

        override fun runSoftware() {
           print("运行软件")
        }

        override fun withKeyBoard(KeyBoardName: String) {
            super.withKeyBoard(KeyBoardName)
            //print("$KeyBoardName")
        }

    }
}