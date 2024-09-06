package com.md29.husein.myblogposttest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.md29.husein.myblogposttest.databinding.ItemRowTagBinding

class TagAdapter(
    private val postList: List<String>,
) : RecyclerView.Adapter<TagAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TagAdapter.ListViewHolder {
        val binding = ItemRowTagBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TagAdapter.ListViewHolder,
        position: Int,
    ) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    inner class ListViewHolder(
        private var binding: ItemRowTagBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            binding.tvTag.text = tag
        }

    }
}