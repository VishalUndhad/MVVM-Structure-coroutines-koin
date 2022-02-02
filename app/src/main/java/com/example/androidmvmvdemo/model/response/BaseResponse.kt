package com.example.androidmvmvdemo.model.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("status")
    var status: Boolean = false,
    @SerializedName("error")
    var error: String = "",
    @SerializedName("popup_message")
    var popupMessage: String = ""
)
