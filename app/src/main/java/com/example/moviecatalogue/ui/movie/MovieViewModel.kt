package com.example.moviecatalogue.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.helper.SingleEvent

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    init {
        discoverMovies()
    }

    val listMovies: LiveData<List<Movie>>
        get() = movieRepository.listMovies

    val errorDiscoverMovies: LiveData<SingleEvent<Boolean>>
        get() = movieRepository.errorDiscoverMovies

    fun discoverMovies() =
        movieRepository.discoverMovies()
}