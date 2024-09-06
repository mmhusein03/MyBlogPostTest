package com.md29.husein.myblogposttest.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BlogResponse(
	val total: Int,
	val limit: Int,
	val skip: Int,
	val posts: List<PostsItem>
) : Parcelable

@Parcelize
data class Reactions(
	val dislikes: Int,
	val likes: Int
) : Parcelable

@Parcelize
data class PostsItem(
	val reactions: Reactions,
	val id: Int,
	val title: String,
	val body: String,
	val userId: Int,
	val views: Int,
	val tags: List<String>
) : Parcelable
