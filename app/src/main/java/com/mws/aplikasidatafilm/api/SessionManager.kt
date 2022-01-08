package com.mws.aplikasidatafilm.api

import android.content.Context
import android.content.SharedPreferences
import com.mws.aplikasidatafilm.R

class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object{
        const val ACCESS_TOKEN = "access_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return  prefs.getString(ACCESS_TOKEN, null)
    }


}