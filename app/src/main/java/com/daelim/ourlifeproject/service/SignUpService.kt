package com.daelim.ourlifeproject.service

import com.daelim.ourlifeproject.data.SignInRequest
import retrofit2.Call
import com.daelim.ourlifeproject.data.SignUpRequestBody
import com.daelim.ourlifeproject.data.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {

    @Headers("accept: application/json","content--Type: application/json")
    @POST("users")
    fun requestSignUp(@Body signUpRequestBody: SignUpRequestBody) : Call<SignUpResponse>
}