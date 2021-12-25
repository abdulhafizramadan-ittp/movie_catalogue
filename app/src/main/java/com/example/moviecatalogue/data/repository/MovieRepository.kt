package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.response.MovieDetailResponse
import com.example.moviecatalogue.data.response.MovieResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import com.example.moviecatalogue.helper.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>> = _listMovies

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    private val _errorDiscoverMovies = MutableLiveData<SingleEvent<Boolean>>()
    val errorDiscoverMovies: LiveData<SingleEvent<Boolean>> = _errorDiscoverMovies

    private val _movieDetailError = MutableLiveData<SingleEvent<Boolean>>()
    val movieDetailError: LiveData<SingleEvent<Boolean>> = _movieDetailError


    fun discoverMovies() {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getDiscoverMovie(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    _errorDiscoverMovies.value = SingleEvent(false)
                    _listMovies.value = response.body()?.results?.map {
                        it.toDomain()
                    }
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    _errorDiscoverMovies.value = SingleEvent(true)
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }
            })
    }

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