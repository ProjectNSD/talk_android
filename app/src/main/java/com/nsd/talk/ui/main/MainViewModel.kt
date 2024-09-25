package com.nsd.talk.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsd.talk.data.MessageRepository
import com.nsd.talk.model.RegisterModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val messageRepository = MessageRepository()
    fun register(token: String) {
        viewModelScope.launch {
            val model = RegisterModel("test", "01000000000", token)
            Log.d("mainViewModel", "registerModel: $model")
            val response = messageRepository.register(model)
            Log.d("MainViewModel", "response: ${token}")
            if (response.isSuccessful) {
                Log.d("MainViewModel", "token Successful")
            }
        }
    }
}