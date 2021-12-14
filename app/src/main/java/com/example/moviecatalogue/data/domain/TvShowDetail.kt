package com.example.moviecatalogue.data.domain

import com.google.gson.annotations.SerializedName

data class TvShowDetail(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val numberOfEpisodes: Int,
    val type: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val name: String,
    val tagline: String,
    val id: Int,
    val numberOfSeasons: Int,
    val lastAirDate: String,
    val homepage: String,
    val status: String
)
