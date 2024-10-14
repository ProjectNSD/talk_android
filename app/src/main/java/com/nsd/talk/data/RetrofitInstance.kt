package com.nsd.talk.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL ="https://ee7a-211-193-229-239.ngrok-free.app/"
    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val gson = GsonBuilder().setLenient().create()

    val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()


    fun getInstance(): Retrofit {
        return client
    }
}