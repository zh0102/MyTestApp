package com.zh.myalgorithm.test2

import android.view.View

/**
 * @ClassName: MyDialogOpera
 * @Description: 封装类
 * @Author: ZHW
 * @Date: 2022/10/17 下午3:51
 */
sealed class MyDialogOpera {
    object Show : MyDialogOpera()
    object Hide : MyDialogOpera()
    class TranslateX(val x: Float):MyDialogOpera()

    fun execute(view: View, opera: MyDialogOpera) = when (opera) {
        MyDialogOpera.Show -> view.visibility = View.VISIBLE
        MyDialogOpera.Hide -> view.visibility = View.GONE
        is MyDialogOpera.TranslateX -> view.translationX = opera.x
    }

}