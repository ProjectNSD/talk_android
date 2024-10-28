package com.nsd.talk.data.repository.api

import com.nsd.talk.data.retrofit.Api
import com.nsd.talk.data.retrofit.RetrofitInstance
import com.nsd.talk.model.PhoneNumbersModel
import com.nsd.talk.model.ServerContactModel
import retrofit2.Response

class PhoneNumbersRepository {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(Api::class.java)

    suspend fun registerCheck(phoneNumbers: PhoneNumbersModel): Response<List<ServerContactModel>> {
        val data = service.registerCheck(phoneNumbers)
        return data
    }
}