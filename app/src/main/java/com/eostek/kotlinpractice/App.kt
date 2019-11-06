package com.eostek.kotlinpractice

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.squareup.leakcanary.LeakCanary
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init(this)
    }

    private fun init(app: Application) {
        application = app;
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
    }

    companion object {
        lateinit var application: Application
        var secondDialogDefault: Int = 0
        fun getContext(): Context {
            return application.applicationContext
        }

        /**
         * 切换全局语言配置
         * @param es 是英文
         */
        fun setLanguage(es: Boolean?) {
            val resources = application.resources// 获得res资源对象
            val config = resources.configuration// 获得设置对象
            val dm = resources.displayMetrics// 获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale(if (es!!) "en" else Constant.ZHONGWEN, if (es) "US" else "CN")// 设置APP语言设置为英文
            resources.updateConfiguration(config, dm)
        }

        //判断app是否存活
        fun isAppAlive(context:Context,packageName:String) :Boolean{
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val processInfos : List<ActivityManager.RunningAppProcessInfo> =activityManager.runningAppProcesses
            for(e in processInfos){
                return TextUtils.equals(e.processName,packageName)
            }
            return false
        }
    }


}