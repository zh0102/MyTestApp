package com.zh.ktapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @ClassName: BaseActivity
 * @Description: activity基础类
 * @Author: ZHW
 * @Date: 2022/10/13 上午10:21
 */
abstract class BaseActivity<VM : BaseViewModel>(private val contentViewResId: Int) :
    AppCompatActivity() {
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResId)
        initViewModel()
        initView()
        initData()
    }


    private fun initViewModel(){
        //注意：actualTypeArguments[0] 0-->指上面BaseActivity<VM : BaseViewModel, VB : ViewDataBinding>的VM放在第一个
        val type: Class<VM> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        mViewModel = ViewModelProvider(this).get(type)
        mViewModel.start()
    }

    //初始化UI
    abstract fun initView()
    //初始化数据
    abstract fun initData()

}