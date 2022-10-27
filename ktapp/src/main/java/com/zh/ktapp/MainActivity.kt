package com.zh.ktapp

import android.util.Log
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.Observer
import com.zh.ktapp.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main) {
    private val TAG = "MainActivity"
    private lateinit var bt1: Button
    private lateinit var bt2: Button
    private var btGet: Button? = null
    private var btSend: Button? = null
    private var tvContext: TextView? = null

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun initView() {
        bt1 = findViewById(R.id.bt_add)
        bt2 = findViewById(R.id.bt_remove)
        tvContext = findViewById(R.id.tv_context)
        btGet = findViewById(R.id.get)
        btSend = findViewById(R.id.send)
        mViewModel.studentData?.observe(this, Observer {
            Log.i(TAG, "initView: " + it)
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            tvContext?.text = it.toString()
        })
        bt1.setOnClickListener(OnClickListener {
            mViewModel.insertStudent()
            getData()
        })
        bt2.setOnClickListener {
            mViewModel.deleteStudent()
            getData()
        }

    }

    override fun initData() {
        mViewModel.initStudent()
        getData()
    }

    //获取天气数据
    private fun getData() {
        mViewModel.getStudent()
    }
}