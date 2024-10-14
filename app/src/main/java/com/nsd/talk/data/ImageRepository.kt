package com.nsd.talk.data

import com.nsd.talk.model.RegisterModel
import com.nsd.talk.model.SuccessModel
import retrofit2.Response

class ImageRepository {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(Api::class.java)

    suspend fun getProfileImage(phoneNumber: String): Response<String> {
        val data = service.getProfileImage(phoneNumber)
        return data
    }
}