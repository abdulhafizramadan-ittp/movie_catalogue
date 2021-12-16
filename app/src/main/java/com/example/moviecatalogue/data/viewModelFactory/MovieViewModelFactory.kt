package com.example.moviecatalogue.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.ui.movie.MovieViewModel
import java.lang.IllegalArgumentException

class MovieViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(movieRepository) as T
            else -> throw IllegalArgumentException("Un register view model : ${modelClass.name}")
        }
    }
}