package com.example.moviecatalogue.data.domain

import com.google.gson.annotations.SerializedName

data class TvShow(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val name: String,
    val voteAverage: Double,
    val id: Int,
    val posterPath: String
)
