package com.daelim.ourlifeproject.data

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("Authorization") val Authorization:String,
    @SerializedName("result") val result:String
)
