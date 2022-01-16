package com.example.moviecatalogue.di

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.local.LocalDataSource
import com.example.moviecatalogue.data.local.database.MovieDatabase
import com.example.moviecatalogue.data.remote.RemoteDataSource
import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.example.moviecatalogue.data.remote.api.ApiService
import com.example.moviecatalogue.ui.detail.movie.MovieDetailViewModel
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailViewModel
import com.example.moviecatalogue.ui.favorite.FavoriteViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

val appModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(get())
                    .collector(ChuckerCollector(get()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
    single {
        Room.databaseBuilder(get(), MovieDatabase::class.java, "movie_catalogue_db")
            .build()
            .movieDao()
    }
    single { Executors.newSingleThreadExecutor() }
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    single { MovieRepository(get(), get()) }

    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}