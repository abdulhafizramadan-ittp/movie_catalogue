package com.example.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getAllFavoriteMovies()

    fun getAllFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> =
        movieRepository.getAllFavoriteTvShows()
}