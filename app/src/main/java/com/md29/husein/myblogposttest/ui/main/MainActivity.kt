package com.md29.husein.myblogposttest.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.md29.husein.myblogposttest.R
import com.md29.husein.myblogposttest.adapter.BlogAdapter
import com.md29.husein.myblogposttest.data.response.PostsItem
import com.md29.husein.myblogposttest.databinding.ActivityMainBinding
import com.md29.husein.myblogposttest.ui.detail.DetailActivity
import com.md29.husein.myblogposttest.utils.ShowLoading
import com.md29.husein.myblogposttest.utils.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var showLoading: ShowLoading
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showLoading = ShowLoading()
        showRecycleView()
        setUpAction()
    }

    private fun setUpAction() {
        mainViewModel.apply {
            lifecycleScope.launch {
                getPost()
            }
            post.observe(this@MainActivity) {
                getBlogPost(it)
            }
            isLoading.observe(this@MainActivity) {
                showLoading.showLoading(it, binding.progressBar)
            }
        }
    }

    private fun getBlogPost(post: List<PostsItem>) {
        binding.rvPost.adapter = BlogAdapter(post) {
            val moveDetailPost = Intent(this@MainActivity, DetailActivity::class.java)
            moveDetailPost.putExtra(DetailActivity.DETAIL_POST, it)
            startActivity(moveDetailPost)
        }
    }

    private fun showRecycleView() {
        val layoutManager = LinearLayoutManager(this)
        if (this.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvPost.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvPost.layoutManager = layoutManager
        }
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider)!!)
        binding.rvPost.addItemDecoration(itemDecoration)
    }
}