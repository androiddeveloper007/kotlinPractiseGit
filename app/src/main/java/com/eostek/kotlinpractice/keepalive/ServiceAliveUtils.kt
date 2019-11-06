package com.eostek.kotlinpractice.keepalive

import android.app.ActivityManager
import android.content.Context

class ServiceAliveUtils {
    companion object{
        fun isServiceAlice(mContext: Context): Boolean {
            var isServiceRunning = false
            val manager = mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager ?: return true
            for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
                if ("com.eostek.kotlinpractice.keepalive.DownloadService" == service.service.className) {
                    isServiceRunning = true
                }
            }
            return isServiceRunning
        }
    }
}