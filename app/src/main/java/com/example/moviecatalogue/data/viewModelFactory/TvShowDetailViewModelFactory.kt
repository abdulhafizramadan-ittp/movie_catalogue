package com.example.moviecatalogue.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.repository.TvShowDetailRepository
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailViewModel
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel

class TvShowDetailViewModelFactory(private val tvShowDetailRepository: TvShowDetailRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> TvShowDetailViewModel(tvShowDetailRepository) as T
            else -> throw IllegalArgumentException("Un register view model : ${modelClass.name}")
        }
    }
}