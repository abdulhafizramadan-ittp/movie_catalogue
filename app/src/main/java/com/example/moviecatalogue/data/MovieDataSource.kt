package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail

interface MovieDataSource {
    fun discoverMovies(): LiveData<List<Movie>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun discoverTvShows(): LiveData<List<TvShow>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail>
}