package com.eostek.kotlinpractice.keepalive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

//屏幕锁屏广播接受器工具类
class ReceiverUtil(context:Context) {
    lateinit var screenReceiver:ScreenBroadcastReceiver
    var screenStateListener:ScreenStateListener? = null
    var mContext = context

    inner class ScreenBroadcastReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if(screenStateListener!=null){
                when(action){
                    Intent.ACTION_SCREEN_ON -> screenStateListener!!.onScreenOn()
                    Intent.ACTION_SCREEN_OFF -> screenStateListener!!.onScreenOff()
                    Intent.ACTION_USER_PRESENT -> screenStateListener!!.onUserPresent()
                }
            }
        }
    }

    interface ScreenStateListener{
        fun onScreenOn()
        fun onScreenOff()
        fun onUserPresent()
    }

    fun setScreenReceiverListener(listen : ScreenStateListener) {
        this.screenStateListener = listen
        //动态启动广播接收器
        this.screenReceiver = ScreenBroadcastReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        mContext.registerReceiver(screenReceiver,filter)
    }

    fun stopScreenReceiverListener(){
        mContext.unregisterReceiver(screenReceiver)
    }
}