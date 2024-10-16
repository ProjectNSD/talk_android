package com.nsd.talk.ui.friend

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsd.talk.data.ImageRepository
import com.nsd.talk.data.PhoneNumbersRepository
import com.nsd.talk.data.SharedPreferenceRepository
import com.nsd.talk.model.UserContactModel
import com.nsd.talk.model.PhoneNumbersModel
import com.nsd.talk.model.ServerContactModel
import com.nsd.talk.util.Constant
import kotlinx.coroutines.launch


class FriendViewModel : ViewModel() {
    private val imageRepository = ImageRepository()
    private val phoneNumbersRepository = PhoneNumbersRepository()
    private val contacts = mutableListOf<UserContactModel>()
    private lateinit var serverContacts: List<ServerContactModel>
    val profileLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val serverContactsLiveData: MutableLiveData<List<ServerContactModel>> by lazy {
        MutableLiveData<List<ServerContactModel>>()
    }
    fun registerCheck() {
        viewModelScope.launch {
            val phoneNumberContacts = mutableListOf<String>()
            contacts.forEach { contact ->
                phoneNumberContacts.add(contact.phoneNumber)
            }
            val model = PhoneNumbersModel(phoneNumberContacts)
            val response = phoneNumbersRepository.registerCheck(model)
            Log.d("FriendViewModel", "response: ${response}")
            if (response.isSuccessful) {
                serverContactsLiveData.value = response.body()!!
                Log.d("FriendViewModel", "registerCheck")
            }
        }
    }

    fun getContact(context: Context) {
        val resolver: ContentResolver = context.contentResolver
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val cursor = resolver.query(phoneUri, projection, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val nameIndex = cursor.getColumnIndex(projection[1])
                val numberIndex = cursor.getColumnIndex(projection[2])
                val name = cursor.getString(nameIndex)
                var number = cursor.getString(numberIndex)
                number = number.replace("-", "")
                contacts.add(UserContactModel(name, number))
            }
        }
        cursor!!.close()
    }

    fun getProfile(context: Context) {
        val prefRepository = SharedPreferenceRepository(context)
        val phoneNumber = prefRepository.getStringValue(Constant.PHONE_NUMBER)
        viewModelScope.launch {
            val response = imageRepository.getProfileImage(phoneNumber)
            if (response.isSuccessful) {
                Log.d("tag", "image url: ${response.body()}")
                profileLiveData.value = response.body()?.profileUrl
            }
        }
    }

    fun getServerContact() = serverContacts
}