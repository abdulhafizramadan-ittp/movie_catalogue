package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.repository.MovieDetailRepository
import com.example.moviecatalogue.data.response.MovieDetailResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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