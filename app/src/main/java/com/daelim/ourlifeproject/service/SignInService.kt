package com.daelim.ourlifeproject.service

import com.daelim.ourlifeproject.data.SignInRequest
import com.daelim.ourlifeproject.data.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {

    @Headers("Content-Type: application/json")
    @POST("users/login")
    fun requestSignIn(@Body signInRequest: SignInRequest) : Call<SignInResponse>

}