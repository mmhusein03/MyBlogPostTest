package com.md29.husein.myblogposttest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.md29.husein.myblogposttest.data.response.PostsItem
import com.md29.husein.myblogposttest.databinding.ItemRowPostBinding

class BlogAdapter(
    private val postList: List<PostsItem>,
    private val onClick: (PostsItem) -> Unit
) : RecyclerView.Adapter<BlogAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlogAdapter.ListViewHolder {
        val binding = ItemRowPostBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BlogAdapter.ListViewHolder,
        position: Int
    ) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    inner class ListViewHolder(
        private var binding: ItemRowPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postsItem: PostsItem) {
            binding.apply {
                tvTitle.text = postsItem.title
                tvLike.text = postsItem.reactions.likes.toString()
                tvView.text = postsItem.views.toString()
            }
            itemView.setOnClickListener {
                onClick(postsItem)
            }
        }

    }
}