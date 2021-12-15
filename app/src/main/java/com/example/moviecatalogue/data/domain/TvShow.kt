package com.example.moviecatalogue.data.domain

data class TvShow(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val name: String,
    val voteAverage: Double,
    val id: Int,
    val posterPath: String
)
