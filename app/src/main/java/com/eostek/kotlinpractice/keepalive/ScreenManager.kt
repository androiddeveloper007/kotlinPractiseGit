package com.eostek.kotlinpractice.keepalive

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

class ScreenManager {
    companion object {
        var instance: ScreenManager?=null
        fun getInstance(context: Context): ScreenManager {
            if (instance == null) {
                instance = ScreenManager()
            }
            return instance as ScreenManager
        }
    }

    lateinit var context: Context
    var mActivity: WeakReference<Activity>? = null

    fun setSingleActivity(act: Activity) {
        mActivity = WeakReference<Activity>(act)
    }

    fun startActivity() {
        val intent = Intent(context, SinglePixelActivity().javaClass)//MainActivity::class.java
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun finishActivity() {
        if (mActivity != null) {
            val activity = mActivity!!.get()
            if (activity != null) {
                activity.finish()
            }
        }
    }

}