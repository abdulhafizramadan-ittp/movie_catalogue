package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.repository.TvShowDetailRepository
import com.example.moviecatalogue.data.response.TvShowDetailResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailViewModel(private val tvShowDetailRepository: TvShowDetailRepository) : ViewModel() {
    val tvShowDetail: LiveData<TvShowDetail>
        get() = tvShowDetailRepository.tvShowDetail

    val tvShowDetailError: LiveData<Boolean>
        get() = tvShowDetailRepository.tvShowDetailError

    fun getTvShowDetail(tvShowId: Int) =
        tvShowDetailRepository.getTvShowDetail(tvShowId)

    fun setTvShowDetailError(state: Boolean) =
        tvShowDetailRepository.setTvShowDetailError(state)
}