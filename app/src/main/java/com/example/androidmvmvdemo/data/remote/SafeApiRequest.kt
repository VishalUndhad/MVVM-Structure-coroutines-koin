package com.example.androidmvmvdemo.data.remote

import android.content.Context
import android.util.Log
import com.example.androidmvmvdemo.BuildConfig
import com.example.androidmvmvdemo.R
import com.example.androidmvmvdemo.model.response.BaseResponse
import com.example.androidmvmvdemo.utils.isNetworkConnected
import com.example.androidmvmvdemo.utils.logMsgError
import com.example.androidmvmvdemo.utils.showToast
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class SafeApiRequest(private val mContext: Context) {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Resource<T> {
        val response = call.invoke()
        if (BuildConfig.DEBUG)
            response.body().toString().logMsgError("Api Response")
        if (response.isSuccessful) {
            val baseResponse = (response.body() as BaseResponse)

            if (response.isSuccessful && baseResponse.status) {
                response.body()?.let {
                    return Resource.success(it, baseResponse.message)
                }
            }
        } else {
            Log.e("App response", "Reponse----" + response);
            if (response.code() == 403) {
                return Resource.logout(null)
            } else if (response.code() == 502) {
                return Resource.error("", code = response.code())
            }
            if (response.code() != 200) {
                return Resource.logout(null)
            }

            when (response.errorBody()) {
                is HttpException -> {
                    when {
                        response.code() == 401 -> {
                            return Resource.error(
                                response.message(),
                                code = response.code()
                            )
                        }
                        response.code() == 500 -> {
                            return Resource.error(
                                response.message(),
                                code = response.code()
                            )
                        }
                        response.code() == 503 -> {
                            return Resource.error(
                                response.message(),
                                code = response.code()
                            )
                        }
                        else -> {
                            return Resource.error(
                                mContext.getString(R.string.server_connection),
                                code = response.code()
                            )
                        }
                    }
                }
                is SocketTimeoutException -> {
                    return Resource.error(
                        mContext.getString(R.string.try_again_to),
                        code = response.code()
                    )
                }
                is IOException -> {
                    return if (mContext.isNetworkConnected()) {
                        Resource.error(
                            mContext.getString(R.string.server_connection),
                            code = response.code()
                        )
                    } else {
                        Resource.error(
                            mContext.getString(R.string.internet_connection),
                            code = response.code()
                        )
                    }
                }
                else -> return Resource.error(
                    response.message(),
                    code = response.code()
                )
            }
            return Resource.error(
                response.message(),
                code = response.code()
            )
        }
        return Resource.error(mContext.getString(R.string.internet_connection))
    }
}

fun callLogout(isShowMessage: Boolean = true) {

}

