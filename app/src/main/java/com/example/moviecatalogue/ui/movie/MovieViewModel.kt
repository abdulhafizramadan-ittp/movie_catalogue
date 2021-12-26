package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.Movie

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun discoverMovies(): LiveData<List<Movie>> =
        movieRepository.discoverMovies()

}