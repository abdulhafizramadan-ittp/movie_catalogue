package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.SingleEvent

class FakeTvShowDetailViewModel(
    private val tvShowRepository: TvShowRepository,
) : ViewModel() {

    val tvShowDetail: LiveData<TvShowDetail>
        get() = tvShowRepository.tvShowDetail

    val tvShowDetailError: LiveData<SingleEvent<Boolean>>
        get() = tvShowRepository.tvShowDetailError

    fun getTvShowDetail(tvShowId: Int) =
        tvShowRepository.getTvShowDetail(tvShowId)
}