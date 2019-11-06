package com.eostek.kotlinpractice.memory

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eostek.kotlinpractice.R
import java.lang.ref.WeakReference

class Crash1: AppCompatActivity() {
    companion object{
        var mContext : Context? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crash1)
        crash()
    }

    fun crash() {
//        var weakReference = WeakReference<Context>(this)
//        mContext = weakReference.get();
        mContext = this
    }

    override fun onDestroy() {
        super.onDestroy()
//        mContext = null
    }
}