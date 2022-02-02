package com.example.androidmvmvdemo.data.remote

import java.io.IOException

class APIException(message: String, errorCode: Int) : Exception(message) {

}

class NoInternetException(message: String) : IOException(message)