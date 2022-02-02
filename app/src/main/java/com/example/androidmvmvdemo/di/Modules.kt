package com.example.androidmvmvdemo.di

import android.content.Context
import com.app.cpass.preference.UserPreference
import com.example.androidmvmvdemo.BuildConfig
import com.example.androidmvmvdemo.data.remote.ApiService
import com.example.androidmvmvdemo.data.repositories.DataRepository
import com.example.androidmvmvdemo.data.repositories.DataRepositoryImpl
import com.example.androidmvmvdemo.ui.splash.SplashViewModel
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_API_URL = "https://dev.coronatest.co.uk/"

val appModules = module {
    single {
        UserPreference(context = get())
    }

    // The Retrofit service using our custom HTTP client instance as a singleton
    single() {
        createWebService<ApiService>(
            okHttpClient = createHttpClient(preferenceProvider = get(), context = get()),
            baseUrl = BuildConfig.BASE_URL
        )
    }

    // Tells Koin how to create an instance of Repository
    factory<DataRepository> { DataRepositoryImpl(webApi = get(), context = get()) }

    // Specific viewModel pattern to tell Koin how to build ViewModel
    viewModel {
        SplashViewModel(dataRepository = get())
    }
}

fun createHttpClient(preferenceProvider: UserPreference, context: Context): OkHttpClient {
    return OkHttpClient().newBuilder()
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor(preferenceProvider))
        .addInterceptor(provideLoggingInterceptor())
        .addInterceptor(ChuckInterceptor(context))
        .build()
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

