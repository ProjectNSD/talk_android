package com.nsd.talk.ui.friend

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nsd.talk.databinding.FragmentFriendBinding
import com.nsd.talk.ui.chat.ChatActivity
import com.nsd.talk.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FriendFragment : Fragment() {
    private val friendAdapter by lazy {
        FriendAdapter(object : FriendAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val intent = Intent(activity, ChatActivity::class.java)
                startActivity(intent)
            }
        })
    }

    companion object {
        fun newInstance() = FriendFragment()
    }

    private lateinit var viewModel: FriendViewModel
    private lateinit var binding: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[FriendViewModel::class.java]
        setupUi()
        return binding.root
    }

    private fun setupUi() {
        setContact()
        viewModel.getProfile(requireContext())
        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { profile ->
            if (profile != null) {
                Glide
                    .with(this@FriendFragment)
                    .load(profile)
                    .centerCrop()
                    .into(binding.ivProfile);
            }
        })
    }

    private fun setContact() {
        lifecycleScope.launch {
            if (viewModel.hasContact(requireContext())) {
                viewModel.getDBContact(requireContext())
            } else {
                viewModel.getUserContact(requireContext())
                viewModel.registerCheck()
            }
        }
        viewModel.serverContactsLiveData.observe(viewLifecycleOwner, Observer { contacts ->
            friendAdapter.setContacts(contacts)
            binding.rcvFriend.adapter = friendAdapter
            binding.rcvFriend.setHasFixedSize(false)
            binding.rcvFriend.layoutManager = LinearLayoutManager(requireContext())
        })
    }
}