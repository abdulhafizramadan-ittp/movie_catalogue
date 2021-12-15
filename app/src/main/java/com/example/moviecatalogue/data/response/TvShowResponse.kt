package com.example.moviecatalogue.data.response

import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<TvShowItem>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

data class TvShowItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_name")
    val name: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)

fun TvShowItem.toDomain(): TvShow =
    TvShow(
        firstAirDate = firstAirDate ?: "-",
        overview = overview ?: "-",
        originalLanguage = originalLanguage ?: "-",
        name = name ?: "-",
        voteAverage = voteAverage ?: 0.0,
        id = id ?: 0,
        posterPath = ApiConfig.POSTER_MD + posterPath
    )