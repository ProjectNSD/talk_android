package com.nsd.talk.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nsd.talk.data.MessageRepository
import com.nsd.talk.data.SharedPreferenceRepository

class RegisterViewModel : ViewModel() {
    private val messageRepository = MessageRepository()
    fun isFirstStartApp(context: Context): Boolean {
        val repository = SharedPreferenceRepository(context)
        return repository.isTrue("FIRST_START")
    }

    fun setFirstStartAppPreference(context: Context) {
        val repository = SharedPreferenceRepository(context)
        repository.setBooleanValue("FIRST_START", true)
    }
}