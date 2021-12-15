package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.response.MovieDetailResponse
import com.example.moviecatalogue.data.response.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    private val _movieDetailError = MutableLiveData(false)
    val movieDetailError: LiveData<Boolean> = _movieDetailError

    fun getMovieDetail(movieId: Int) {
        ApiConfig.getInstance().getMovieDetail(movieId, ApiConfig.TMDB_TOKEN).enqueue(object : Callback<MovieDetailResponse?> {
            override fun onResponse(
                call: Call<MovieDetailResponse?>,
                response: Response<MovieDetailResponse?>
            ) {
                if (response.body() != null) {
                    _movieDetail.value = response.body()?.toDomain()
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse?>, t: Throwable) {
                _movieDetailError.value = true
            }
        })
    }
}