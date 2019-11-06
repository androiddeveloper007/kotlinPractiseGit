package com.eostek.kotlinpractice.keepalive

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log

class ScheduleService:JobService() {
    companion object{
        val TAG = ScheduleService::class.java.name
    }
    override fun onStopJob(params: JobParameters?): Boolean {
        val isServiceRunning = ServiceAliveUtils.isServiceAlice(applicationContext)
        if (!isServiceRunning) {
            val i = Intent(this, DownloadService::class.java)
            startService(i)
            Log.d(TAG, "ScheduleService启动了DownloadService")
        }
        jobFinished(params, false)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        return false
    }
}