package com.example.moviecatalogue.data.remote

import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.example.moviecatalogue.data.remote.api.ApiService
import com.example.moviecatalogue.data.remote.response.*
import com.example.moviecatalogue.helper.instrumentTest.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private var apiService: ApiService) {

    fun discoverMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        apiService.getDiscoverMovie(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    callback.onAllMoviesReceived(response.body()?.results)
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }
            })
    }

    fun getMovieDetail(callback: LoadMovieDetailCallback, movieId: Int) {
        EspressoIdlingResource.increment()
        apiService.getMovieDetail(movieId, ApiConfig.TMDB_TOKEN).enqueue(object :
            Callback<MovieDetailResponse?> {
            override fun onResponse(
                call: Call<MovieDetailResponse?>,
                response: Response<MovieDetailResponse?>
            ) {
                callback.onMovieDetailReceived(response.body())
                EspressoIdlingResource.ifNotIdlingDecrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse?>, t: Throwable) {
                EspressoIdlingResource.ifNotIdlingDecrement()
            }
        })
    }

    fun discoverTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        apiService.getDiscoverTvShow(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<TvShowResponse?> {
                override fun onResponse(
                    call: Call<TvShowResponse?>,
                    response: Response<TvShowResponse?>
                ) {
                    callback.onAllTvShowReceived(response.body()?.results)
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }

                override fun onFailure(call: Call<TvShowResponse?>, t: Throwable) {
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }
            })
    }

    fun getTvShowDetail(callback: LoadTvShowDetailCallback, tvShowId: Int) {
        EspressoIdlingResource.increment()
        apiService.getTvShowDetail(tvShowId, ApiConfig.TMDB_TOKEN).enqueue(object :
            Callback<TvShowDetailResponse?> {
            override fun onResponse(
                call: Call<TvShowDetailResponse?>,
                response: Response<TvShowDetailResponse?>
            ) {
                callback.onTvShowDetailReceived(response.body())
                EspressoIdlingResource.ifNotIdlingDecrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse?>, t: Throwable) {
                EspressoIdlingResource.ifNotIdlingDecrement()
            }
        })
    }


    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieItemResponses: List<MovieItem>?)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse?)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowItemResponse: List<TvShowItem>?)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse?)
    }
}