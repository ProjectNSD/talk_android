package com.nsd.talk.ui.chatroom

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import com.nsd.talk.R
import com.nsd.talk.databinding.FragmentChatRoomBinding

class ChatRoomFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: FragmentChatRoomBinding
    companion object {
        fun newInstance() = ChatRoomFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room, container, false)
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

        }
    }

}