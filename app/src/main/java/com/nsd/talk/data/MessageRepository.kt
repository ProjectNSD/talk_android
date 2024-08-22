package com.nsd.talk.data

import retrofit2.Response

class MessageRepository {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(Api::class.java)

    suspend fun sendMessage(msg: String): Response<String> {
        val data = service.postSendMsg("", "")
        return data
    }
}