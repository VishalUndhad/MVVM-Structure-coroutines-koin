package com.example.androidmvmvdemo.data.repositories

import com.example.androidmvmvdemo.data.remote.Resource
import com.example.androidmvmvdemo.model.response.InitResponse
import com.example.androidmvmvdemo.data.remote.UseCaseResult

interface DataRepository {

    suspend fun getInit(type: String, versionName: String): Resource<InitResponse>
}