package com.md29.husein.myblogposttest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.md29.husein.myblogposttest.data.repo.BlogRepository
import com.md29.husein.myblogposttest.data.response.PostsItem

class MainViewModel(
    private val blogRepository: BlogRepository
) : ViewModel() {
    val post: LiveData<List<PostsItem>> = blogRepository.post
    val isLoading: LiveData<Boolean> = blogRepository.isLoading

    suspend fun getPost() = blogRepository.getBlogPost()

}