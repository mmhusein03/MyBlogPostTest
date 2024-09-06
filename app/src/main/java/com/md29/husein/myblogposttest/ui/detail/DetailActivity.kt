package com.md29.husein.myblogposttest.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.md29.husein.myblogposttest.R
import com.md29.husein.myblogposttest.adapter.TagAdapter
import com.md29.husein.myblogposttest.data.response.PostsItem
import com.md29.husein.myblogposttest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showRecycleView()
        setDetailPost()
        setupAction()
    }

    @Suppress("DEPRECATION")
    private fun setupAction() {
        binding.fabBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setDetailPost() {
        val detailPost = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DETAIL_POST, PostsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DETAIL_POST)
        }

        binding.apply {
            tvTitle.text = detailPost?.title
            tvDescription.text = detailPost?.body
            tvView.text = detailPost?.views.toString()
            tvLike.text = detailPost?.reactions?.likes.toString()
            rvTags.adapter = TagAdapter(detailPost!!.tags)
        }
    }

    private fun showRecycleView() {
        val layoutManager =
            LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTags.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider)!!)
        binding.rvTags.addItemDecoration(itemDecoration)

    }

    companion object {
        const val DETAIL_POST = "detail_post"
    }

}