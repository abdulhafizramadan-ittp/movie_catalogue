package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.repository.MovieDetailRepository

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {
    val movieDetail: LiveData<MovieDetail>
        get() = movieDetailRepository.movieDetail

    val movieDetailError: LiveData<Boolean>
        get() = movieDetailRepository.movieDetailError

    fun getMovieDetail(movieId: Int) =
        movieDetailRepository.getMovieDetail(movieId)

    fun setMovieDetailError(state: Boolean) =
        movieDetailRepository.setMovieDetailError(state)
}