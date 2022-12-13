package com.example.retrofittestapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    val BASE_URL = "https://cataas.com/"

    val catsApi = getInstance().create(ApiService::class.java)

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}