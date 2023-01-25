package com.daelim.ourlifeproject.`object`

import com.daelim.ourlifeproject.interceptor.AuthInterceptor
import com.daelim.ourlifeproject.service.SignInService
import com.daelim.ourlifeproject.service.SignUpService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiObject {
    private const val BASE_URL = "http://ec2-52-194-219-234.ap-northeast-1.compute.amazonaws.com:8080"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .client(provideokHttpClient(AuthInterceptor()))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun provideokHttpClient(interceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder().run{
        addInterceptor(interceptor)
        build()

    }

    val signUpService : SignUpService = retrofit.create(SignUpService::class.java)
    val signInService : SignInService = retrofit.create(SignInService::class.java)

}