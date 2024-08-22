package com.nsd.talk.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("v1/msgRev/{userId}/{msg}")
    fun getMsg(
        @Path("userId") id: String,
        @Path("msg") msg: String
    ): Response<String>

    @POST("v1/msgSend")
    suspend fun postSendMsg(
        @Path("userId") id: String,
        @Path("msg") msg: String
    ): Response<String>
}