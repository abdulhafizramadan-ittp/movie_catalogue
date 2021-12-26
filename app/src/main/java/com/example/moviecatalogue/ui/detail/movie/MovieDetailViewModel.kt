package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.helper.viewModel.SingleEvent

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail> =
        movieRepository.getMovieDetail(movieId)

}