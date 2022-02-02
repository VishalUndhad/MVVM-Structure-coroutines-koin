package com.example.androidmvmvdemo.data.repositories

import android.content.Context
import com.example.androidmvmvdemo.R
import com.example.androidmvmvdemo.model.response.InitResponse
import com.example.androidmvmvdemo.data.remote.ApiService
import com.example.androidmvmvdemo.data.remote.Resource
import com.example.androidmvmvdemo.data.remote.SafeApiRequest
import com.example.androidmvmvdemo.data.remote.UseCaseResult

class DataRepositoryImpl(private val webApi: ApiService, private val context: Context) :
    DataRepository, SafeApiRequest(context) {

    override suspend fun getInit(type: String, versionName: String): Resource<InitResponse> {
        return try {
            apiRequest { webApi.init(type, versionName) }
        } catch (e: Exception) {
            Resource.error(context.getString(R.string.internet_connection))
        }
    }
}