package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.remote.api.ApiConfig
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

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("original_name")
    val name: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)

fun TvShowItem.toDomain(): TvShow =
    TvShow(
        id = id ?: 0,
        name = name ?: "-",
        posterPath = ApiConfig.POSTER_MD + posterPath
    )