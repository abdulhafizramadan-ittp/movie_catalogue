package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.repository.TvShowDetailRepository
import com.example.moviecatalogue.helper.SingleEvent

class TvShowDetailViewModel(private val tvShowDetailRepository: TvShowDetailRepository) : ViewModel() {
    val tvShowDetail: LiveData<TvShowDetail>
        get() = tvShowDetailRepository.tvShowDetail

    val tvShowDetailError: LiveData<SingleEvent<Boolean>>
        get() = tvShowDetailRepository.tvShowDetailError

    fun getTvShowDetail(tvShowId: Int) =
        tvShowDetailRepository.getTvShowDetail(tvShowId)

}