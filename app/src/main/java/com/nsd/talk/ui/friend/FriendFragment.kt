package com.nsd.talk.ui.friend

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.nsd.talk.R
import com.nsd.talk.databinding.FragmentFriendBinding

class FriendFragment : Fragment() {
    companion object {
        fun newInstance() = FriendFragment()
    }

    private lateinit var viewModel: FriendViewModel
    private lateinit var binding: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FriendViewModel::class.java)
        viewModel.getContact(requireContext())
        viewModel.registerCheck()
        viewModel.getProfile(requireContext())
        viewModel.profileLiveData.observe(viewLifecycleOwner, Observer { profile ->
            if (profile.isNotBlank() || profile.isNotEmpty()) {
                Glide
                    .with(this@FriendFragment)
                    .load(profile)
                    .centerCrop()
                    .into(binding.ivProfile);
            }
        })
    }

}