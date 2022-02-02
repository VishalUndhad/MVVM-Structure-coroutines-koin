package com.example.androidmvmvdemo.ui.splash

import com.example.androidmvmvdemo.BuildConfig
import com.example.androidmvmvdemo.base.BaseViewModel
import com.example.androidmvmvdemo.data.remote.Resource
import com.example.androidmvmvdemo.data.repositories.DataRepository
import com.example.androidmvmvdemo.model.response.InitResponse
import com.example.androidmvmvdemo.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(private val dataRepository: DataRepository) : BaseViewModel() {

    val initLiveData = SingleLiveEvent<Resource<InitResponse>>()

    init {
        getInit()
    }

    private fun getInit() {
        launch {
            initLiveData.value = Resource.loading(null)

            val result = withContext(Dispatchers.IO) {
                dataRepository.getInit(
                    "android",
                    BuildConfig.VERSION_NAME
                )
            }
            initLiveData.value = result
        }
    }
}