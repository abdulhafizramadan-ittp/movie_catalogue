package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.response.MovieDetailResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import com.example.moviecatalogue.helper.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailRepository {
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    private val _movieDetailError = MutableLiveData<SingleEvent<Boolean>>()
    val movieDetailError: LiveData<SingleEvent<Boolean>> = _movieDetailError

    fun getMovieDetail(movieId: Int) {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getMovieDetail(movieId, ApiConfig.TMDB_TOKEN).enqueue(object :
            Callback<MovieDetailResponse?> {
            override fun onResponse(
                call: Call<MovieDetailResponse?>,
                response: Response<MovieDetailResponse?>
            ) {
                if (response.body() != null) {
                    _movieDetailError.value = SingleEvent(false)
                    _movieDetail.value = response.body()?.toDomain()
                }
                EspressoIdlingResource.ifNotIdlingDecrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse?>, t: Throwable) {
                _movieDetailError.value = SingleEvent(true)
                EspressoIdlingResource.ifNotIdlingDecrement()
            }
        })
    }
}