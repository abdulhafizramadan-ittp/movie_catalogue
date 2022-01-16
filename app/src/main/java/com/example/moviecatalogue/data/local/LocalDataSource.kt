package com.example.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.local.database.MovieDao
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity
import java.util.concurrent.ExecutorService

class LocalDataSource(
    private val movieDao: MovieDao,
    private val executorService: ExecutorService
) {

    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        LivePagedListBuilder(
            movieDao.getAllFavoriteMovies(),
            10
        ).build()

    fun getAllFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> =
        LivePagedListBuilder(
            movieDao.getAllFavoriteTvShows(),
            10
        ).build()

    fun getMovieById(id: Int): MovieEntity? =
        movieDao.getMovieById(id)

    fun getTvShowById(id: Int): TvShowEntity? =
        movieDao.getTvShowById(id)

    fun insertFavoriteMovie(movieEntity: MovieEntity) =
        executorService.execute { movieDao.insertFavoriteMovie(movieEntity) }

    fun insertFavoriteTvShow(tvShowEntity: TvShowEntity) =
        executorService.execute { movieDao.insertFavoriteTvShow(tvShowEntity) }

    fun deleteFavoriteMovie(movieEntity: MovieEntity) =
        executorService.execute { movieDao.deleteFavoriteMovie(movieEntity) }

    fun deleteFavoriteTvShow(tvShowEntity: TvShowEntity) =
        executorService.execute { movieDao.insertFavoriteTvShow(tvShowEntity) }
}