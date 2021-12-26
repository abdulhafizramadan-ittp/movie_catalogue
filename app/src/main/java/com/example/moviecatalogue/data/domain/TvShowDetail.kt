package com.example.moviecatalogue.data.domain

data class TvShowDetail(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val type: String,
    val posterPath: String,
    val voteAverage: Double,
    val name: String,
    val tagline: String,
    val id: Int,
    val numberOfSeasons: Int,
    val lastAirDate: String,
    val status: String
)

