package com.example.moviecatalogue.data.domain

import com.example.moviecatalogue.data.remote.response.TvShowItem

data class TvShow(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val name: String,
    val voteAverage: Double,
    val id: Int,
    val posterPath: String
)

