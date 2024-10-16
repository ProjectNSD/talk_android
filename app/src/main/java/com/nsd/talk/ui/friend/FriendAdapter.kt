package com.nsd.talk.ui.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsd.talk.R
import com.nsd.talk.databinding.ItemFriendBinding
import com.nsd.talk.model.ServerContactModel

class FriendAdapter : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {
    private var contacts: List<ServerContactModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_friend,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        with(holder.binding) {
            tvName.text = contacts[position].name
            Glide
                .with(ivProfile.context)
                .load(contacts[position].profileUrl)
                .centerCrop()
                .into(ivProfile)
        }
    }

    fun setContacts(contacts: List<ServerContactModel>) {
        this.contacts = contacts
    }

    inner class FriendViewHolder(val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}