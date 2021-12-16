package com.example.moviecatalogue.data.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.ui.tvShow.TvShowViewModel

class TvShowViewModelFactory(private val tvShowRepository: TvShowRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(tvShowRepository) as T
            else -> throw IllegalArgumentException("Un register view model : ${modelClass.name}")
        }
    }
}