package com.md29.husein.myblogposttest.utils.di

import com.md29.husein.myblogposttest.data.repo.BlogRepository
import com.md29.husein.myblogposttest.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(): BlogRepository {
        val apiService = ApiConfig.getApiService()
        return BlogRepository(apiService)
    }
}