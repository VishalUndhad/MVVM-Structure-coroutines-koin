package com.app.cpass.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.androidmvmvdemo.model.response.LoginResponse
import com.google.gson.Gson

open class UserPreference constructor(
    private val context: Context
) {
    private val PREF_DEVICE_TOKEN = "device_token"
    private val PREF_USER_DATA = "loggedData"
    private val PREF_SELECTED_LANGUAGE = "selectedLanguage"

    private val appContext = context.applicationContext
    private val sharedPref: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveDeviceToken(token: String) {
        sharedPref.edit().putString(PREF_DEVICE_TOKEN, token).apply()
    }

    fun getDeviceToken(): String {
        return sharedPref.getString(PREF_DEVICE_TOKEN, "") ?: ""
    }

    fun saveLoggedData(response: LoginResponse.UserData) {
        sharedPref.edit().putString(PREF_USER_DATA, Gson().toJson(response)).apply()
    }

    fun getLoggedData(): String? {
        return sharedPref.getString(PREF_USER_DATA, null)
    }

    fun getApiKey(): String {
        return if (getLoggedData() == null) {
            //default api key
            "oscgksw00ggg484ow8sw4sko0coss0cwo0888sko"
        } else {
            Gson().fromJson(getLoggedData(), LoginResponse.UserData::class.java).apiKey
        }
    }

    fun getUserId(): String {
        return if (getLoggedData() == null) {
            ""
        } else {
            Gson().fromJson(getLoggedData(), LoginResponse.UserData::class.java).id
        }
    }

    fun getMemberId(): Int {
        return if (getLoggedData() == null) {
            0
        } else {
            Gson().fromJson(getLoggedData(), LoginResponse.UserData::class.java).memberId
        }
    }

    fun getSelectedLanguage(): String {
        return sharedPref.getString(PREF_SELECTED_LANGUAGE, "en") ?: "en"
    }

    fun saveLanguage(language: String) {
        sharedPref.edit().putString(PREF_SELECTED_LANGUAGE, language).apply()
    }

    fun clearPreference() {
        sharedPref.edit().clear().apply()
    }
}