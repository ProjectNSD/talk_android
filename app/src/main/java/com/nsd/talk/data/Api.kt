package com.nsd.talk.data

import com.nsd.talk.model.RegisterModel
import com.nsd.talk.model.SuccessModel
import retrofit2.Response
import retrofit2.http.Body
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
    ): Response<SuccessModel>

    @POST("v1/register")
    suspend fun registerToken(
        @Body token: RegisterModel
    ):Response<SuccessModel>
}