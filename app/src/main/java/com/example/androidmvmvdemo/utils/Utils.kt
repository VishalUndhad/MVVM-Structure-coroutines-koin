package com.example.androidmvmvdemo.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.logMsgError(tag: String) {
    Log.e(tag, this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}