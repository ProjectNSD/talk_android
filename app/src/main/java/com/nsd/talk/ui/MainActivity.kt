package com.nsd.talk.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.nsd.talk.R
import com.nsd.talk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
                    replaceFragment(ChatFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.action_setting -> {
                    replaceFragment(SettingsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }
}