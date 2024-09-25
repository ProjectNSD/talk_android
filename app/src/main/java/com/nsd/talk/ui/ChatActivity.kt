package com.nsd.talk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsd.talk.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private val chatAdapter by lazy { ChatAdapter() }
    private val binding by lazy { ActivityChatBinding.inflate(layoutInflater) }
    private val viewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        chatAdapter.addText("1")
        chatAdapter.addText("2")
        chatAdapter.addText("3")
        chatAdapter.addText("4")
        setUi()
    }

    private fun setUi() {
        with(binding) {
            rcvChat.adapter = chatAdapter
            rcvChat.setHasFixedSize(false)
            rcvChat.layoutManager = LinearLayoutManager(this@ChatActivity)

            btnChatSend.setOnClickListener {
                viewModel.sendMsg()
            }

            viewModel.sendMessageResponse.observe(this@ChatActivity, Observer {
                Log.d("ChatActivity", "observe: $it")
            })
        }
    }
}