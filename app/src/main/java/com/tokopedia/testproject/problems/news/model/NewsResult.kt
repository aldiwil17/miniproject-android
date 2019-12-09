package com.tokopedia.testproject.problems.news.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsResult(
        @SerializedName("status")
        @Expose
        val status: String,
        @SerializedName("totalResults")
        @Expose
        val totalResults: Int,
        @SerializedName("articles")
        @Expose
        val articles: List<Article>? = null)

data class Source(

        @SerializedName("id")
        @Expose
        val id: String,
        @SerializedName("name")
        @Expose
        val name: String)

@Entity
data class Article(
        @PrimaryKey
        val uid: String = UUID.randomUUID().toString(),
        @SerializedName("title")
        @ColumnInfo(name = "title")
        @Expose
        val title: String? = null,
        @SerializedName("description")
        @ColumnInfo(name = "description")
        @Expose
        val description: String? = null,
        @SerializedName("urlToImage")
        @ColumnInfo(name = "urlToImage")
        @Expose
        val urlToImage: String? = null,
        @SerializedName("publishedAt")
        @ColumnInfo(name = "publishedAt")
        @Expose
        val publishedAt: String? = null,
        @SerializedName("content")
        @ColumnInfo(name = "content")
        @Expose
        val content: String? = null)