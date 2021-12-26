package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.TvShowDetail

class TvShowDetailViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail> =
        movieRepository.getTvShowDetail(tvShowId)

}