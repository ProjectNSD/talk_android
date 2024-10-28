package com.nsd.talk.data.repository.api

import com.nsd.talk.data.retrofit.Api
import com.nsd.talk.data.retrofit.RetrofitInstance
import com.nsd.talk.model.ImageModel
import retrofit2.Response

class ImageRepository {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(Api::class.java)

    suspend fun getProfileImage(phoneNumber: String): Response<ImageModel> {
        val data = service.getProfileImage(phoneNumber)
        return data
    }
}