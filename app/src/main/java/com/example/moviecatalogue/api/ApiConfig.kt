package com.example.moviecatalogue.api

import com.example.moviecatalogue.BuildConfig

object ApiConfig {
    const val TMDB_TOKEN = BuildConfig.TMDB_TOKEN
    const val POSTER_MD = "http://image.tmdb.org/t/p/w500"
    const val BASE_URL = "https://api.themoviedb.org/3/"
}