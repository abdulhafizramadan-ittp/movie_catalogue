package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.remote.response.TvShowDetailResponse
import com.example.moviecatalogue.data.remote.response.TvShowResponse
import com.example.moviecatalogue.data.remote.response.toDomain
import com.example.moviecatalogue.helper.instrumentTest.EspressoIdlingResource
import com.example.moviecatalogue.helper.viewModel.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository {
    private val _listTvShows = MutableLiveData<List<TvShow>>()
    val listTvShows: LiveData<List<TvShow>> = _listTvShows

    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    val tvShowDetail: LiveData<TvShowDetail> = _tvShowDetail

}