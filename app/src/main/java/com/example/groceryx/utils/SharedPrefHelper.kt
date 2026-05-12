package com.example.groceryx.utils


import android.content.Context
import com.example.groceryx.utils.Constants.KEY_IS_LOGGED_IN
import com.example.groceryx.utils.Constants.KEY_MOBILE
import com.example.groceryx.utils.Constants.PREF_NAME

object SharedPrefHelper {

    fun saveMobile(context: Context, mobile: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_MOBILE, mobile).putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }

    fun getMobile(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_MOBILE, "") ?: ""
    }

    fun isLoggedIn(context: Context): Boolean {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun clearAll(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }
}
