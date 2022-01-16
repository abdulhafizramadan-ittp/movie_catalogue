package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.local.entity.TvShowEntity

class TvShowDetailViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail> =
        movieRepository.getTvShowDetail(tvShowId)

    fun getTvShowById(id: Int): LiveData<TvShowEntity> =
        movieRepository.getTvShowById(id)

    fun insertFavoriteTvShow(tvShowEntity: TvShowEntity) =
        movieRepository.insertFavoriteTvShow(tvShowEntity)

    fun deleteFavoriteTvShow(tvShowEntity: TvShowEntity) =
        movieRepository.deleteFavoriteTvShow(tvShowEntity)
}