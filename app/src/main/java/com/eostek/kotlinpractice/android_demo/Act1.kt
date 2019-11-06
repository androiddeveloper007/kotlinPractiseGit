package com.eostek.kotlinpractice.android_demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eostek.kotlinpractice.R
import kotlinx.android.synthetic.main.activity_main.*

class Act1: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //kotlin中控件可以直接使用，无需find viewbyid
        tv1.text = "erbi"

        //class的表达
//        startActivity(Intent(this, SecondAct::class.java))//方式1
        startActivity(Intent(this, SecondAct().javaClass))//方式2
    }
}