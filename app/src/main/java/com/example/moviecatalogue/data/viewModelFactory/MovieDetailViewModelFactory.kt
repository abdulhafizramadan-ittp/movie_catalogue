package com.example.moviecatalogue.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.repository.MovieDetailRepository
import com.example.moviecatalogue.ui.detail.movie.MovieDetailViewModel

class MovieDetailViewModelFactory(private val movieDetailRepository: MovieDetailRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(movieDetailRepository) as T
            else -> throw IllegalArgumentException("Un register view model : ${modelClass.name}")
        }
    }
}