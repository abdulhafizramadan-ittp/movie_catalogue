package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.response.TvShowDetailResponse
import com.example.moviecatalogue.data.response.TvShowResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import com.example.moviecatalogue.helper.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository {
    private val _listTvShows = MutableLiveData<List<TvShow>>()
    val listTvShows: LiveData<List<TvShow>> = _listTvShows

    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    val tvShowDetail: LiveData<TvShowDetail> = _tvShowDetail

    private val _errorDiscoverTvShows = MutableLiveData<SingleEvent<Boolean>>()
    val errorDiscoverTvShows: LiveData<SingleEvent<Boolean>> = _errorDiscoverTvShows

    private val _tvShowDetailError = MutableLiveData<SingleEvent<Boolean>>()
    val tvShowDetailError: LiveData<SingleEvent<Boolean>> = _tvShowDetailError


    fun discoverTvShows() {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getDiscoverTvShow(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<TvShowResponse?> {
                override fun onResponse(
                    call: Call<TvShowResponse?>,
                    response: Response<TvShowResponse?>
                ) {
                    _errorDiscoverTvShows.value = SingleEvent(false)
                    _listTvShows.value = response.body()?.results?.map {
                        it.toDomain()
                    }
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }

                override fun onFailure(call: Call<TvShowResponse?>, t: Throwable) {
                    _errorDiscoverTvShows.value = SingleEvent(true)
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }
            })
    }

    fun getTvShowDetail(tvShowId: Int) {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getTvShowDetail(tvShowId, ApiConfig.TMDB_TOKEN).enqueue(object :
            Callback<TvShowDetailResponse?> {
            override fun onResponse(
                call: Call<TvShowDetailResponse?>,
                response: Response<TvShowDetailResponse?>
            ) {
                _tvShowDetailError.value = SingleEvent(false)
                _tvShowDetail.value = response.body()?.toDomain()
                EspressoIdlingResource.ifNotIdlingDecrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse?>, t: Throwable) {
                _tvShowDetailError.value = SingleEvent(true)
                EspressoIdlingResource.ifNotIdlingDecrement()
            }
        })
    }

}