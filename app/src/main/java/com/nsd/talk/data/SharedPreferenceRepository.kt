package com.nsd.talk.data

import android.content.Context

class SharedPreferenceRepository(context: Context) {
    private val pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun setBooleanValue(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }
    fun isTrue(key: String): Boolean = pref.getBoolean(key, false)
}