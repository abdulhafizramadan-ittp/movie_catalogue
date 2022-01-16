package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity

interface MovieDataSource {
    fun discoverMovies(): LiveData<List<Movie>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun discoverTvShows(): LiveData<List<TvShow>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail>

    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getAllFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getMovieById(id: Int): MovieEntity?

    fun getTvShowById(id: Int): TvShowEntity?

    fun insertFavoriteMovie(movieEntity: MovieEntity)

    fun insertFavoriteTvShow(tvShowEntity: TvShowEntity)

    fun deleteFavoriteMovie(movieEntity: MovieEntity)

    fun deleteFavoriteTvShow(tvShowEntity: TvShowEntity)
}