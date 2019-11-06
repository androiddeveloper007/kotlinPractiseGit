package com.eostek.kotlinpractice.keepalive

import android.app.Notification
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class GuardService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return object : KeepAliveConnection.Stub() {}
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, Notification())
        bindService(Intent(this, StepService::class.java), object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                val isServiceRunning = ServiceAliveUtils.isServiceAlice(applicationContext)
                if (!isServiceRunning) {
                    val i = Intent(this@GuardService, DownloadService::class.java)
                    startService(i)
                }
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                // 断开链接
                startService(Intent(this@GuardService, StepService::class.java))
                // 重新绑定
                bindService(
                    Intent(this@GuardService, StepService::class.java),
                    this,
                    Context.BIND_IMPORTANT
                )
            }

        }, Context.BIND_IMPORTANT)
        return START_STICKY
    }

}