package com.example.moviecatalogue.data.remote.api

import com.example.moviecatalogue.data.remote.response.MovieDetailResponse
import com.example.moviecatalogue.data.remote.response.MovieResponse
import com.example.moviecatalogue.data.remote.response.TvShowDetailResponse
import com.example.moviecatalogue.data.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") apiKey: String
    ) : Call<MovieResponse>

    @GET("discover/tv")
    fun getDiscoverTvShow(
        @Query("api_key") apiKey: String
    ) : Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetailResponse>

    @GET("tv/{tv_show_id}")
    fun getTvShowDetail(
        @Path("tv_show_id") tvShowId: Int,
        @Query("api_key") apiKey: String
    ) : Call<TvShowDetailResponse>
}