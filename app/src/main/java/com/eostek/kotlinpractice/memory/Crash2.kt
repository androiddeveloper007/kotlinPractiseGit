package com.eostek.kotlinpractice.memory

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.eostek.kotlinpractice.R
import java.lang.ref.WeakReference

class Crash2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crash1)
        crash()
    }

    fun crash() {
//        var mHandler = weakHandler(this)
//        mHandler.sendEmptyMessageDelayed(1, 10000)

        handler.sendEmptyMessageDelayed(1, 10000)
    }

    //声明一个静态匿名内部类
    //kotlin内部类默认是静态的
    class weakHandler(act: Activity) : Handler() {
        var weakAct : WeakReference<Activity> = WeakReference(act)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(weakAct.get()!=null){
                Log.e("crash2", "handler message is doing")
            }
        }
    }

    //internal修饰符，表示在本模块的所有可以访问到声明区域的均可以访问该类的所有 internal 成员
    //内部类默认是静态的，若实现为非静态，需要使用inner
    //匿名内部类不能使用inner修饰，所以kotlin中匿名内部类都是静态的。
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}