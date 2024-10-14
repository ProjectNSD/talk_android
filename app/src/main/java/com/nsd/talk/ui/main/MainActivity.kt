package com.nsd.talk.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nsd.talk.R
import com.nsd.talk.databinding.ActivityMainBinding
import com.nsd.talk.ui.chatroom.ChatRoomFragment
import com.nsd.talk.ui.setting.SettingsFragment
import com.nsd.talk.ui.friend.FriendFragment
import com.nsd.talk.ui.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() = with(binding) {
        navBottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_friend -> {
                    replaceFragment(FriendFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.action_chat -> {
                    replaceFragment(ChatRoomFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.action_setting -> {
                    replaceFragment(SettingsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_main, FriendFragment())
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }
}