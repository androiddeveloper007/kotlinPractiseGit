package com.eostek.kotlinpractice.android_demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eostek.kotlinpractice.R
import com.eostek.kotlinpractice.views.GifView

class GifViewAct:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gifdemo)
        val gif = findViewById<GifView>(R.id.gif)
        gif.setGifResource(R.drawable.aa)
    }

}