package com.example.retrofittestapp.retrofit

import com.example.catspracticeapp.model.RandomCatsDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cat?json=true")
    suspend fun getRandomCat() : Response<RandomCatsDto>
}