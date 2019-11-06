package com.eostek.kotlinpractice.keepalive

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.eostek.kotlinpractice.App

class SinglePixelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setGravity(Gravity.LEFT and Gravity.TOP)
        window.attributes.x = 0
        window.attributes.y = 0
        window.attributes.width = 1
        window.attributes.height = 1
        ScreenManager.getInstance(this).setSingleActivity(this)
    }

    override fun onDestroy() {
        if(!App.isAppAlive(this, packageName)) {
            val intent = Intent(this, DownloadService::class.java)
            startService(intent)
        }
        super.onDestroy()
    }
}