package com.zh.ktapp.base

import androidx.lifecycle.ViewModel

/**
 * @ClassName: BaseViewModel
 * @Description:
 * @Author: ZHW
 * @Date: 2022/10/13 上午9:31
 */
abstract class BaseViewModel : ViewModel() {
    //初始化数据
    abstract fun start()
}