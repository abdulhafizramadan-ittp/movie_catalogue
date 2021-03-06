package com.example.moviecatalogue.data.local.database

import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity ORDER BY id DESC")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM TvShowEntity ORDER BY id DESC")
    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM MovieEntity WHERE id == :id")
    fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM TvShowEntity WHERE id == :id")
    fun getTvShowById(id: Int): TvShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(tvShow: TvShowEntity)

    @Delete
    fun deleteFavoriteMovie(movie: MovieEntity)

    @Delete
    fun deleteFavoriteTvShow(tvShow: TvShowEntity)
}