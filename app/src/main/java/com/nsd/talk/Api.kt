package com.nsd.talk

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("v1/msgRev/{userId}/{msg}")
    fun getMsg(
        @Path("userId") id : String,
        @Path("msg") msg : String
    ) : Call<String>

    @POST("v1/msgSend")
    fun postSendMsg(
        @Path("userId") id : String,
        @Path("msg") msg : String
    ) : Call<String>
}