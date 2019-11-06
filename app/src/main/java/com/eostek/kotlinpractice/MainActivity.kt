package com.eostek.kotlinpractice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.eostek.kotlinpractice.android_demo.GifViewAct
import com.eostek.kotlinpractice.keepalive.DownloadService
import com.eostek.kotlinpractice.keepalive.GuardService
import com.eostek.kotlinpractice.keepalive.StepService
import com.eostek.kotlinpractice.memory.Crash1
import com.eostek.kotlinpractice.memory.Crash2
import com.eostek.kotlinpractice.sqlite_demo.SQliteDemoAct
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //进程保活
        val intent = Intent(this, DownloadService::class.java)
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        //双守护线程，优先级不一样
        startAllServices()
    }

    fun crash1(view: View) {
        startActivity(Intent(this, Crash1::class.java))
    }

    fun crash2(view: View) {
        startActivity(Intent(this, Crash2::class.java))
    }

    fun crash3(view: View) {
        startActivity(Intent(this, GifViewAct::class.java))
    }

    fun sqldemo(view: View) {
        startActivity(Intent(this, SQliteDemoAct::class.java))
    }

    //进程保活
    var downloadBinder:DownloadService.DownloadBinder?=null
    val serviceConnection = object:ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder= service as DownloadService.DownloadBinder?
            downloadBinder!!.setOnTimeChangeListener(object:DownloadService.OnTimeChangeListener{
                override fun showTime(time: String) {
                    runOnUiThread { tv_show_time.text=time }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
    }

    /**
     * 开启所有守护Service
     */
    private fun startAllServices() {
        startService(Intent(this, StepService::class.java))
        startService(Intent(this, GuardService::class.java))
    }
}
