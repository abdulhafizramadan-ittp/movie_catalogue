package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.response.TvShowDetailResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailRepository {
    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    val tvShowDetail: LiveData<TvShowDetail> = _tvShowDetail

    private val _tvShowDetailError = MutableLiveData(false)
    val tvShowDetailError: LiveData<Boolean> = _tvShowDetailError

    fun getTvShowDetail(tvShowId: Int) {
        ApiConfig.getInstance().getTvShowDetail(tvShowId, ApiConfig.TMDB_TOKEN).enqueue(object :
            Callback<TvShowDetailResponse?> {
            override fun onResponse(
                call: Call<TvShowDetailResponse?>,
                response: Response<TvShowDetailResponse?>
            ) {
                if (response.body() != null) {
                    _tvShowDetailError.value = false
                    _tvShowDetail.value = response.body()?.toDomain()
                }
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse?>, t: Throwable) {
                _tvShowDetailError.value = true
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
    }

    fun setTvShowDetailError(state: Boolean) {
        _tvShowDetailError.value = state
    }
}