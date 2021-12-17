package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.repository.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val listMovies: LiveData<List<Movie>>
        get() = movieRepository.listMovies

    val errorDiscoverMovies: LiveData<Boolean>
        get() = movieRepository.errorDiscoverMovies

    fun discoverMovies() =
        movieRepository.discoverMovies()

    fun setErrorDiscoverMovies(state: Boolean) =
        movieRepository.setErrorDiscoverMovies(state)
}