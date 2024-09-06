package com.md29.husein.myblogposttest.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.md29.husein.myblogposttest.data.response.PostsItem
import com.md29.husein.myblogposttest.data.retrofit.ApiService

class BlogRepository(
    private val apiService: ApiService,
) {

    private val _post = MutableLiveData<List<PostsItem>>()
    val post: LiveData<List<PostsItem>> = _post


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    suspend fun getBlogPost() {
        _isLoading.value = true
        try {
            val response = apiService.getPost()
            _isLoading.value = false
            if (response.isSuccessful) {
                _post.value = response.body()?.posts
                Log.d("WWK", response.body().toString())
            }
        } catch (e: Exception) {
            Log.e(TAG1, "onFailure: ${e.message}")
        }
    }

    companion object {
        private const val TAG1 = "MainActivity"
        private const val TAG2 = "DetailFragment"
    }
}