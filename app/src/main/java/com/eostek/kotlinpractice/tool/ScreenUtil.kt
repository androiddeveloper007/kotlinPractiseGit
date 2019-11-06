package com.eostek.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import com.eostek.kotlinpractice.App

class ScreenUtil {
    companion object {
        private val displayMetrics = getDisplayMetrics()

        private fun getDisplayMetrics(): DisplayMetrics {
            val wm: WindowManager = App.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getRealMetrics(displayMetrics)
            return displayMetrics
        }

        fun dp2px(dp: Int): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
        }

        fun sp2px(sp: Int): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), displayMetrics)
        }

        fun px2dp(px: Int): Int {
            return (px / displayMetrics.density + 0.5f).toInt()
        }

        fun px2sp(pxValue: Int): Int {
            return (pxValue / displayMetrics.scaledDensity + 0.5f).toInt()
        }

        fun getScreenWidth(): Int {
            return displayMetrics.widthPixels
        }

        fun getScreenHeight(): Int {
            return displayMetrics.heightPixels
        }

        fun getStatusBarHeight(activity: Activity): Int {
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            return frame.top
        }

        fun getStatusBarHeight(context: Context): Int {
            return try {
                @SuppressLint("PrivateApi") val c = Class.forName("com.android.internal.R\$dimen")
                val `object` = c.newInstance()
                val field = c.getField("status_bar_height")
                val x = field.get(`object`) as Int
                context.resources.getDimensionPixelSize(x)
            } catch (e: Exception) {
                0
            }
        }

        fun getNavigationBarHeight(context: Context): Int {
            val resources = context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

        fun getWidthDp(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            val density = dm.density//屏幕密度（0.75 / 1.0 / 1.5）
            val width = dm.widthPixels
            return (width / density).toInt()
        }

        fun getDensity(): Float {
            val wm = App.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            return dm.density
        }
    }
}