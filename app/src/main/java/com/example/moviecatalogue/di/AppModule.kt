package com.example.moviecatalogue.di

import com.example.moviecatalogue.data.repository.MovieDetailRepository
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.data.repository.TvShowDetailRepository
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.ui.detail.movie.MovieDetailViewModel
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MovieRepository() }
    single { MovieDetailRepository() }
    single { TvShowRepository() }
    single { TvShowDetailRepository() }

    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
}