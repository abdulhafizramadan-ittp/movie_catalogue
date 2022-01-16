package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.local.LocalDataSource
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity
import com.example.moviecatalogue.data.remote.RemoteDataSource
import com.example.moviecatalogue.data.remote.response.*

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieDataSource {

    override fun discoverMovies(): LiveData<List<Movie>> {
        val listMovies = MutableLiveData<List<Movie>>()

        remoteDataSource.discoverMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMoviesReceived(movieItemResponses: List<MovieItem>?) {
                if (movieItemResponses != null) {
                    val moviesDomain = movieItemResponses.map { it.toDomain() }
                    listMovies.postValue(moviesDomain)
                }
            }
        })

        return listMovies
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieDetail> {
        val movieDetail = MutableLiveData<MovieDetail>()

        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetailResponse?) {
                movieDetail.postValue(movieDetailResponse?.toDomain())
            }
        }, movieId)

        return movieDetail
    }

    override fun discoverTvShows(): LiveData<List<TvShow>> {
        val listTvShows = MutableLiveData<List<TvShow>>()

        remoteDataSource.discoverTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowItemResponse: List<TvShowItem>?) {
                if (tvShowItemResponse != null) {
                    val tvShowsDomain = tvShowItemResponse.map { it.toDomain() }
                    listTvShows.postValue(tvShowsDomain)
                }
            }
        })

        return listTvShows
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail> {
        val tvShowDetail = MutableLiveData<TvShowDetail>()

        remoteDataSource.getTvShowDetail(object : RemoteDataSource.LoadTvShowDetailCallback {
            override fun onTvShowDetailReceived(tvShowDetailResponse: TvShowDetailResponse?) {
                tvShowDetail.postValue(tvShowDetailResponse?.toDomain())
            }
        }, tvShowId)

        return tvShowDetail
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        localDataSource.getAllFavoriteMovies()

    override fun getAllFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> =
        localDataSource.getAllFavoriteTvShows()

    override fun getMovieById(id: Int): LiveData<MovieEntity> =
        localDataSource.getMovieById(id)

    override fun getTvShowById(id: Int): LiveData<TvShowEntity> =
        localDataSource.getTvShowById(id)

    override fun insertFavoriteMovie(movieEntity: MovieEntity) =
        localDataSource.insertFavoriteMovie(movieEntity)

    override fun insertFavoriteTvShow(tvShowEntity: TvShowEntity) =
        localDataSource.insertFavoriteTvShow(tvShowEntity)

    override fun deleteFavoriteMovie(movieEntity: MovieEntity) =
        localDataSource.deleteFavoriteMovie(movieEntity)

    override fun deleteFavoriteTvShow(tvShowEntity: TvShowEntity) =
        localDataSource.deleteFavoriteTvShow(tvShowEntity)

}