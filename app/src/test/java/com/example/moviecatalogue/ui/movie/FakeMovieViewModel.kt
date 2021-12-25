package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.helper.SingleEvent

class FakeMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val listMovies: LiveData<List<Movie>>
        get() = movieRepository.listMovies

    val errorDiscoverMovies: LiveData<SingleEvent<Boolean>>
        get() = movieRepository.errorDiscoverMovies

    fun discoverMovies() =
        movieRepository.discoverMovies()
}