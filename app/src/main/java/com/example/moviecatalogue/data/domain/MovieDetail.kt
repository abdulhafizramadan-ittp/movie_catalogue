package com.example.moviecatalogue.data.domain

data class MovieDetail(
    val backdropPath: String,
    val overview: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Double,
    val runtime: Int,
    val id: Int,
    val title: String,
    val tagline: String,
    val posterPath: String,
    val homepage: String,
    val status: String
)