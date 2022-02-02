package com.example.androidmvmvdemo.data.remote

import com.example.androidmvmvdemo.model.response.InitResponse
import com.example.androidmvmvdemo.data.remote.APIConfig.APP_VERSION
import com.example.androidmvmvdemo.data.remote.APIConfig.TYPE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(APIConfig.INIT_API)
    suspend fun init(
        @Query(TYPE) type: String?,
        @Query(APP_VERSION) app_version: String?
    ): Response<InitResponse>
}