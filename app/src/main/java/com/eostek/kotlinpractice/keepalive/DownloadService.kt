package com.eostek.kotlinpractice.keepalive

import android.app.NotificationManager
import android.app.Service
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class DownloadService : Service() {
    companion object{
        val NOTICE_ID = 100
        val TAG="DownloadService"
    }
    lateinit var downloadBinder : DownloadBinder
    var onTimeChangeListener : OnTimeChangeListener?=null
    lateinit var screenManager:ScreenManager
    lateinit var mScreenListenerUtil : ReceiverUtil
    var runTimer:Timer?=null
    var timeSec = 0
    var timeMin=0
    var timeHour=0

    override fun onBind(intent: Intent?): IBinder? {
        return downloadBinder
    }

    override fun onCreate() {
        super.onCreate()
        //注册锁屏广播监听器
        mScreenListenerUtil = ReceiverUtil(this)
        screenManager = ScreenManager.getInstance(this)
        //对象表达式，定义一个接口实现类
        mScreenListenerUtil.setScreenReceiverListener(object : ReceiverUtil.ScreenStateListener{
            override fun onScreenOn() {
                screenManager.finishActivity()
            }

            override fun onScreenOff() {
                screenManager.startActivity()
            }

            override fun onUserPresent() {
            }
        })
        downloadBinder = DownloadBinder()
        useJobServiceForKeepAlive()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startRunTimer()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        val mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(mManager!=null){
            mManager.cancel(NOTICE_ID)
            stopRunTimer()
        }
    }

    inner class DownloadBinder : Binder(){
        fun setOnTimeChangeListener(listener:OnTimeChangeListener){
            onTimeChangeListener = listener
        }
    }

    interface OnTimeChangeListener{
        fun showTime(time:String)
    }

    fun startRunTimer() {
        val mTask = object : TimerTask() {
            override fun run() {
                timeSec++;
                if (timeSec == 60) {
                    timeSec = 0;
                    timeMin++;
                }
                if (timeMin == 60) {
                    timeMin = 0;
                    timeHour++;
                }
                if (timeHour == 24) {
                    timeSec = 0;
                    timeMin = 0;
                    timeHour = 0;
                }
                val time = "时间为：$timeHour : $timeMin : $timeSec"
                onTimeChangeListener?.showTime(time)
            }
        }
        runTimer = Timer()
        //每隔1s更新一下时间
        runTimer?.schedule(mTask,1000,1000)
    }

    fun stopRunTimer(){
        if(runTimer!=null){
            runTimer?.cancel()
            runTimer = null
        }
    }

    //使用JobScheduler进行保活
    fun useJobServiceForKeepAlive() {
        val jobSchedule = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobSchedule.cancelAll()
        val builder = JobInfo.Builder(1024, ComponentName(packageName,
            ScheduleService::class.java.name
        ))
        //周期设置为了2s
        builder.setPeriodic((1000 * 2).toLong())
        builder.setPersisted(true)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        val schedule = jobSchedule.schedule(builder.build())
        if (schedule <= 0) {
            Log.w(TAG, "schedule error！")
        }
    }
}