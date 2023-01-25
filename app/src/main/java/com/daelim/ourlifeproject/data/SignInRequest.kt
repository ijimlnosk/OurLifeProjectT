package com.daelim.ourlifeproject.data

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
