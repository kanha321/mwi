package com.kanhaji.ktemplate.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE)

    fun saveInt(key: String, value: Int) {
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun saveString(key: String, value: String) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return prefs.getString(key, defaultValue) ?: ""
    }
}