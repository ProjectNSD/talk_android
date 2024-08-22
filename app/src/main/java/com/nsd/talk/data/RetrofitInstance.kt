package com.nsd.talk.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL ="https://faa4-211-193-229-239.ngrok-free.app/"

    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()


    fun getInstance(): Retrofit{
        return client
    }
}