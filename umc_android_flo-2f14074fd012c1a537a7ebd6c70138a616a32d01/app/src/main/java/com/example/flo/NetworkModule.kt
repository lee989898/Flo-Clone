package com.example.flo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit(): Retrofit{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://13.125.121.202")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}