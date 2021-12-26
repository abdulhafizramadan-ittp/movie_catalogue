package com.example.moviecatalogue.di

import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.remote.RemoteDataSource
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.ui.detail.movie.MovieDetailViewModel
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig().getInstance(get()) }
    single { RemoteDataSource(get()) }
    single { MovieRepository(get()) }

    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
}