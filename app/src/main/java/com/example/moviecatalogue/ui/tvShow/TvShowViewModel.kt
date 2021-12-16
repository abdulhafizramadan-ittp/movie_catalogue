package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.data.response.TvShowResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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