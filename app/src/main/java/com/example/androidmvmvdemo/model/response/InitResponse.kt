package com.example.androidmvmvdemo.model.response

import com.google.gson.annotations.SerializedName
class InitResponse(
    @SerializedName("loggedin")
    var loggedin: Int? = null,
    @SerializedName("state")
    var state: Int? = null,
    @SerializedName("tax_price")
    var taxPrice: Int? = null,
    @SerializedName("is_give_consent")
    val isGiveConsent: Int? = null
) : BaseResponse()