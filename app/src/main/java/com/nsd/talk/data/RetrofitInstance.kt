package com.nsd.talk.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL ="https://ee7a-211-193-229-239.ngrok-free.app/"
    val gson = GsonBuilder().setLenient().create()
    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()


    fun getInstance(): Retrofit{
        return client
    }
}