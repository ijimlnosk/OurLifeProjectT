package com.daelim.ourlifeproject.data

import com.google.gson.annotations.SerializedName

data class SignUpRequestBody (
    @SerializedName("email")
    val email : String?,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("birth")
    val birth : String
    )