package com.eostek.kotlin

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.eostek.kotlinpractice.App
import com.eostek.kotlinpractice.Constant
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Field

class Util {
    companion object {
        private var toast: Toast? = null

        fun showToast(str: String) {
            if (toast == null) {
                toast = Toast.makeText(App.getContext(), str, Toast.LENGTH_SHORT)
            }else{
                toast?.setText(str)
            }
            toast?.show()
        }

        fun log(str: String) {
            Log.e("ZZP", str)
        }

        fun fixInputMethodManagerLeak( destContext:Context) {
            val imm = destContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val arr:Array<String> = arrayOf("mCurRootView", "mServedView", "mNextServedView")
            var f: Field
            var obj_get: Any
            for (param:String in arr) {
                try {
                    f = imm.javaClass.getDeclaredField(param)
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    obj_get = f.get(imm) as Any
                    if (obj_get is View) {
                        if (obj_get.context == destContext) {
                            f.set(imm, null);
                        } else {
                            break;
                        }
                    }
                } catch ( t: Throwable) {
                    t.printStackTrace();
                }
            }
        }

        //获取sp中语言值，每次加载时，显示上次语言类型
        fun setAppLanguageDefault() {
            val lan = SpUtils.getString(Constant.LANGUAGE, "")
            if (!TextUtils.isEmpty(lan)) {
                App.setLanguage(lan?.contains(Constant.ENGLISH))
            }
        }

        //获取usb连接数
        fun getUSBConnectCount(): Int {
            return try {
                val mount = Runtime.getRuntime().exec("lsusb")
                val reader = BufferedReader(InputStreamReader(mount.inputStream))
                mount.waitFor()
                var count = 0
                while (reader.readLine() != null) {
                    count++
                    log("count: $count")
                }
                reader.close()
                mount.destroy()
                count
            } catch (e: IOException) {
                e.printStackTrace()
                0
            } catch (e: InterruptedException) {
                e.printStackTrace()
                0
            }
        }
    }

}