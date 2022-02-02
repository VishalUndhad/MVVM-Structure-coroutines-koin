package com.example.androidmvmvdemo.ui.splash

import com.example.androidmvmvdemo.BR
import com.example.androidmvmvdemo.R
import com.example.androidmvmvdemo.base.BaseActivity
import com.example.androidmvmvdemo.data.remote.Resource
import com.example.androidmvmvdemo.databinding.ActivitySplashBinding
import com.example.androidmvmvdemo.utils.LoadingDialog
import com.example.androidmvmvdemo.utils.showToast
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(SplashViewModel::class) {

    override val layoutId: Int
        get() = R.layout.activity_splash
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun init() {

    }

    override fun setObserver() {
        viewModel.initLiveData.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    LoadingDialog.showLoadDialog(this)
                }
                Resource.Status.SUCCESS -> {
                    LoadingDialog.hideLoadDialog()
                }
                Resource.Status.ERROR -> {
                    LoadingDialog.hideLoadDialog()
                    showToast(it.message ?: "")
                }
                Resource.Status.LOGOUT -> {
                    LoadingDialog.hideLoadDialog()
                }
            }
        })
    }
}