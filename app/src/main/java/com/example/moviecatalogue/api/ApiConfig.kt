package com.example.moviecatalogue.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.moviecatalogue.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    fun getInstance(context: Context): ApiService {
        val chuckerInterceptor = OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(chuckerInterceptor)
            .build()
            .create(ApiService::class.java)
    }

    companion object {
        const val TMDB_TOKEN = BuildConfig.TMDB_TOKEN
        const val POSTER_MD = "http://image.tmdb.org/t/p/w500"

        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}