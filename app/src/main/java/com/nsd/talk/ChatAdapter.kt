package com.nsd.talk

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nsd.talk.databinding.ItemLeftChatBinding
import com.nsd.talk.databinding.ItemRightChatBinding
import java.lang.RuntimeException

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataSet: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            Constant.LEFT_TALK -> {
                LeftViewHolder(getViewDataBinding(parent, R.layout.item_left_chat))
            }
            Constant.RIGHT_TALK -> {
                RightViewHolder(getViewDataBinding(parent, R.layout.item_right_chat))
            }
            else -> {
                throw RuntimeException("Error!!! Chat Adapter viewType")
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeftViewHolder) {
            holder.binding.tvLeftChat.text = dataSet[position]
        }
        else if (holder is RightViewHolder) {
            holder.binding.tvRightChat.text = dataSet[position]
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    private fun <T: ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int) : T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }

    fun addText(text: String) {
        dataSet.add(text)
    }

    inner class LeftViewHolder(val binding: ItemLeftChatBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    inner class RightViewHolder(val binding: ItemRightChatBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}