package com.app.final_kumar_shubham.data.SharedPref

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Kumar Shubham
 * 10/1/20
 */
object SharedPrefrenceHandle {

    private lateinit var prefs: SharedPreferences
    private const val PREFS_NAME = "params"
    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE)
    }
    fun read(key: String, value: String): String? {
        return prefs.getString(key, value)
    }
    fun write(key: String,value: String) {
        val editor= prefs.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun clear(){
        val editor= prefs.edit()
        editor.clear()
        editor.apply()
    }
}