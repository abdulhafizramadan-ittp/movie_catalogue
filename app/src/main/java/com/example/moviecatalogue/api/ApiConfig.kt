package com.example.moviecatalogue.api

import com.example.moviecatalogue.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        const val TMDB_TOKEN = BuildConfig.TMDB_TOKEN
        const val POSTER_MD = "http://image.tmdb.org/t/p/w500"

        private const val BASE_URL = "https://api.themoviedb.org/3/"

        @Volatile
        private var INSTANCE: ApiService? = null

        fun getInstance(): ApiService =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
                    .apply {
                        INSTANCE = this
                    }
            }
    }
}