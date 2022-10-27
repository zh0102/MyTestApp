package com.zh.myalgorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zh.myalgorithm.singleton.KLazilyDCLSingleton
import com.zh.myalgorithm.singleton.KLazilySingleton
import com.zh.myalgorithm.singleton.KSingleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         KLazilySingleton.getInstance().doSomething()
        KLazilyDCLSingleton.mInstance.doSomething()
    }
}