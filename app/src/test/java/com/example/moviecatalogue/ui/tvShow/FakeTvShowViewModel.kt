package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.SingleEvent

class FakeTvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    val listTvShows: LiveData<List<TvShow>>
        get() = tvShowRepository.listTvShows

    val errorDiscoverTvShows: LiveData<SingleEvent<Boolean>>
        get() = tvShowRepository.errorDiscoverTvShows

    fun discoverTvShows() =
        tvShowRepository.discoverTvShows()

}