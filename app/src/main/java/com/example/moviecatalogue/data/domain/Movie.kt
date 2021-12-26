package com.example.moviecatalogue.data.domain

import com.example.moviecatalogue.data.remote.response.MovieItem

data class Movie(
    val overview: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
    val id: Int,
    val title: String,
    val posterPath: String
)

