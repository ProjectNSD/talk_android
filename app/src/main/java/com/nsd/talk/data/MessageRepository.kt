package com.nsd.talk.data

import com.nsd.talk.model.RegisterModel
import com.nsd.talk.model.SuccessModel
import retrofit2.Response

class MessageRepository {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(Api::class.java)

    suspend fun sendMessage(msg: String): Response<SuccessModel> {
        val data = service.postSendMsg("", "")
        return data
    }

    suspend fun register(token: RegisterModel): Response<SuccessModel> {
        val data = service.registerToken(token)
        return data
    }
}