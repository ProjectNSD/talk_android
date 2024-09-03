package com.nsd.talk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsd.talk.data.MessageRepository
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val messageRepository = MessageRepository()
    private val _sendMessageResponse = MutableLiveData<String>()
    val sendMessageResponse: LiveData<String> = _sendMessageResponse
    fun sendMsg() {
        viewModelScope.launch {
            val response = messageRepository.sendMessage("")
            if (response.isSuccessful) {
                _sendMessageResponse.postValue(response.body().toString())
            }
        }
    }
}