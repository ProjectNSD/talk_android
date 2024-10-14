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
        isFirstStartApp()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("[MainActivity]", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = token
            Log.d("MainActivity", msg)
            viewModel.register(msg)
        })

        createNotificationChannel("1111", "fcm")
    }

    private fun isFirstStartApp() {
        if(!viewModel.isFirstStartApp(applicationContext)) {
            viewModel.setFirstStartAppPreference(applicationContext)
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
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

    @RequiresApi(Build.VERSION_CODES.O)
    // Notification 수신을 위한 체널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }
}