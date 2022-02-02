package com.example.androidmvmvdemo.model.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("data") var user: UserData = UserData()
) : BaseResponse() {
    data class UserData(
        @SerializedName("member_id") var memberId: Int = 0,
        @SerializedName("otp") var otp: String = "",
        @SerializedName("active") var active: String = "",
        @SerializedName("api_key") var apiKey: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("full_name") var fullName: String = "",
        @SerializedName("id") var id: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("last_login") var lastLogin: String = "",
        @SerializedName("password") var password: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("parent_id") var parentid: String = "",
        @SerializedName("profile_picture") var profile_picture: String? = null,
        @SerializedName("gender") var gender: String = "",
        @SerializedName("date_of_birth") var date_of_birth: String = "",
        @SerializedName("address") var address: String = "",
        @SerializedName("postcode") var postcode: String = "",
        @SerializedName("city") var city: String = "",
        @SerializedName("ethnicity") var ethnicity: String = "",
        @SerializedName("nationality") var nationality: String = "",
        @SerializedName("passport") var passport: String = "",
        @SerializedName("nhs_number") var nhs_number: String = "",
        @SerializedName("is_give_consent") var isGiveConsent: Int? = null,


        @SerializedName("activation_code") var activationCode: String? = "",
        @SerializedName("activation_selector") var activationSelector: String? = "",
        @SerializedName("company") var company: String? = "",
        @SerializedName("created_at") var createdAt: String? = "",
        @SerializedName("created_on") var createdOn: String? = "",
        @SerializedName("deleted_at") var deletedAt: String? = "",
        @SerializedName("driving_lic") var drivingLic: String? = "",
        @SerializedName("first_name") var firstName: String? = "",
        @SerializedName("forgotten_password_code") var forgottenPasswordCode: String? = "",
        @SerializedName("forgotten_password_selector") var forgottenPasswordSelector: String? = "",
        @SerializedName("forgotten_password_time") var forgottenPasswordTime: String? = "",
        @SerializedName("id_card") var idCard: String? = "",
        @SerializedName("ip_address") var ipAddress: String? = "",
        @SerializedName("last_name") var lastName: String? = "",
        @SerializedName("remember_code") var rememberCode: String? = "",
        @SerializedName("remember_selector") var rememberSelector: String? = "",
        @SerializedName("remember_token") var rememberToken: String? = "",
        @SerializedName("stripe_key") var stripeKey: String? = "",
        @SerializedName("updated_at") var updatedAt: String? = "",
        @SerializedName("username") var username: String? = "",
        @SerializedName("country_code") var countryCode: String? = "",

    )
}

