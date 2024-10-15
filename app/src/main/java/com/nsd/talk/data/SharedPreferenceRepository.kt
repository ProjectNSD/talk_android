package com.nsd.talk.data

import android.content.Context

class SharedPreferenceRepository(context: Context) {
    private val pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun setBooleanValue(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun setStringValue(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getStringValue(key: String): String = pref.getString(key, "").toString()

    fun isTrue(key: String): Boolean = pref.getBoolean(key, false)
}