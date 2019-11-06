package com.eostek.kotlin

import android.content.Context
import android.content.SharedPreferences
import com.eostek.kotlinpractice.App

class SpUtils {
    companion object {
        var sp: SharedPreferences = App.getContext().getSharedPreferences("osd", Context.MODE_PRIVATE)

        fun putInt(key: String, value: Int) = sp.edit().putInt(key, value).apply()

        fun getInt(key: String, dValue: Int): Int = sp.getInt(key, dValue)

        fun putBoolean(key: String, value: Boolean) = sp.edit().putBoolean(key, value).apply()

        fun getBoolean(key: String, dValue: Boolean): Boolean? = sp.getBoolean(key, dValue)

        fun putString(key: String, value: String) = sp.edit().putString(key, value).apply()

        fun getString(key: String, dValue: String): String? = sp.getString(key, dValue)

        fun remove(key: String) {
            if (isExist(key)) {
                val editor = sp.edit()
                editor.remove(key)
                editor.apply()
            }
        }

        private fun isExist(key: String): Boolean = sp.contains(key)
    }
}