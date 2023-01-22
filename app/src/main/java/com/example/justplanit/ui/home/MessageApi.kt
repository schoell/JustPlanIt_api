package com.example.justplanit.ui.home

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

object MessageApi {
    val retrofit: Retrofit
    val retrofitService: MessageApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.adviceslip.com")
            .build()
        retrofitService = retrofit.create(MessageApiService::class.java)
    }
}

interface MessageApiService {
    @GET("/advice")
    fun advice(): Call<List<slip>>
}