package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.repository.TvShowRepository

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    val listTvShows: LiveData<List<TvShow>>
        get() = tvShowRepository.listTvShows

    val errorDiscoverTvShows: LiveData<Boolean>
        get() = tvShowRepository.errorDiscoverTvShows

    fun discoverTvShows() =
        tvShowRepository.discoverTvShows()

    fun setErrorDiscoverTvShows(state: Boolean) =
        tvShowRepository.setErrorDiscoverTvShows(state)
}