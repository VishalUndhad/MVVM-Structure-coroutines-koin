package com.example.androidmvmvdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewDataBinding,out VM:BaseViewModel>(clazz: KClass<VM>) : AppCompatActivity() {

    val viewModel: VM by viewModel(clazz)

    lateinit var binding: T
    abstract val layoutId: Int
    abstract val bindingVariable: Int

    abstract fun init()
    abstract fun setObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setObserver()
        init()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
        }
        binding.lifecycleOwner = this
    }

}