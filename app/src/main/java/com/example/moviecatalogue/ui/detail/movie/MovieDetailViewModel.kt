package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.local.entity.MovieEntity

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail> =
        movieRepository.getMovieDetail(movieId)

    fun getMovieById(id: Int): LiveData<MovieEntity> =
        movieRepository.getMovieById(id)

    fun insertFavoriteMovie(movieEntity: MovieEntity) =
        movieRepository.insertFavoriteMovie(movieEntity)

    fun deleteFavoriteMovie(movieEntity: MovieEntity) =
        movieRepository.deleteFavoriteMovie(movieEntity)
}