package com.md29.husein.myblogposttest.data.retrofit

import com.md29.husein.myblogposttest.data.response.BlogResponse
import com.md29.husein.myblogposttest.data.response.PostsItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("post")
    suspend fun getPost(): Response<BlogResponse>
}