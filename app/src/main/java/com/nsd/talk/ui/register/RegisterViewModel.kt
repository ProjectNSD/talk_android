package com.nsd.talk.ui.register

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsd.talk.data.MessageRepository
import com.nsd.talk.data.SharedPreferenceRepository
import com.nsd.talk.model.RegisterModel
import com.nsd.talk.util.Constant
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val messageRepository = MessageRepository()
    var name: String = ""
    var phoneNumber: String = ""

    fun register(token: String) {
        viewModelScope.launch {
            val model = RegisterModel(name, phoneNumber, token)
            Log.d("mainViewModel", "registerModel: $model")
            val response = messageRepository.register(model)
            Log.d("MainViewModel", "response: ${token}")
            if (response.isSuccessful) {
                Log.d("MainViewModel", "token Successful")
            }
        }
    }

    fun isFirstStartApp(context: Context): Boolean {
        val repository = SharedPreferenceRepository(context)
        return repository.isTrue("FIRST_START")
    }

    fun setFirstStartAppPreference(context: Context) {
        val repository = SharedPreferenceRepository(context)
        repository.setBooleanValue("FIRST_START", true)
    }

    fun setUserNameNumber(context: Context) {
        val repository = SharedPreferenceRepository(context)
        repository.setStringValue(Constant.PHONE_NUMBER, phoneNumber)
        repository.setStringValue(Constant.NAME, name)
    }
}