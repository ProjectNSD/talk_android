package com.nsd.talk.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsd.talk.R
import com.nsd.talk.databinding.FragmentChatBinding

class ChatFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: FragmentChatBinding
    private val chatAdapter by lazy { ChatAdapter() }
    companion object {
        fun newInstance() = ChatFragment()
    }

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        chatAdapter.addText("1")
        chatAdapter.addText("2")
        chatAdapter.addText("3")
        chatAdapter.addText("4")
        setUi()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setUi() {
        with(binding) {
            rcvChat.adapter = chatAdapter
            rcvChat.setHasFixedSize(false)
            rcvChat.layoutManager = LinearLayoutManager(context)

            btnChatSend.setOnClickListener {
                viewModel.sendMsg()
            }

            viewModel.sendMessageResponse.observe(viewLifecycleOwner, Observer {
                Log.d("ChatActivity", "observe: $it")
            })
        }
    }

}