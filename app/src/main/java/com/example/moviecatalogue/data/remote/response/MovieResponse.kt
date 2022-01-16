package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieItem>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

data class MovieItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)

fun MovieItem.toDomain(): Movie =
    Movie(
        id = id ?: 0,
        title = title ?: "-",
        posterPath = ApiConfig.POSTER_MD + posterPath
    )