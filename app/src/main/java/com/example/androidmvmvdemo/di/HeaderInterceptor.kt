package com.example.androidmvmvdemo.di

import com.app.cpass.preference.UserPreference
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor(private val preferenceProvider: UserPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request = chain.request().newBuilder()
            //.addHeader("Authorization", "Bearer " + preferenceProvider.getAuthToken())
            .addHeader("x-api-key", preferenceProvider.getApiKey())
            .addHeader("language", preferenceProvider.getSelectedLanguage())
            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI")
            .build()
        return chain.proceed(newRequest)
    }
}