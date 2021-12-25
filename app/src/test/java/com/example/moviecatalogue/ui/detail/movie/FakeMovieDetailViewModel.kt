package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.helper.SingleEvent

class FakeMovieDetailViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    val movieDetail: LiveData<MovieDetail>
        get() = movieRepository.movieDetail

    val movieDetailError: LiveData<SingleEvent<Boolean>>
        get() = movieRepository.movieDetailError

    fun getMovieDetail(movieId: Int) {
        movieRepository.getMovieDetail(movieId)
    }
}