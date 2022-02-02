package com.example.androidmvmvdemo.coreUtils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.example.androidmvmvdemo.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        // Adding Koin modules to our application
        startKoin {
            androidContext(this@CoreApplication)
            modules(appModules)
        }
    }
}