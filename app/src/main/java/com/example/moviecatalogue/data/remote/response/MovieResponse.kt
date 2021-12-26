package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.Movie
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

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)

fun MovieItem.toDomain(): Movie =
    Movie(
        overview = overview ?: "-",
        originalLanguage = originalLanguage ?: "-",
        releaseDate = releaseDate ?: "-",
        voteAverage = voteAverage ?: 0.0,
        id = id ?: 0,
        title = title ?: "-",
        posterPath = ApiConfig.POSTER_MD + posterPath
    )