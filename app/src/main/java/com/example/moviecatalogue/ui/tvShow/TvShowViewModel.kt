package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.repository.TvShowRepository

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun discoverTvShows(): LiveData<List<TvShow>> =
        movieRepository.discoverTvShows()

}