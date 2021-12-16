package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.api.ApiService
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.data.response.MovieResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val listMovies: LiveData<List<Movie>>
        get() = movieRepository.listMovies

    val errorDiscoverMovies: LiveData<Boolean>
        get() = movieRepository.errorDiscoverMovies

    fun discoverMovies() =
        movieRepository.discoverMovies()

    fun setErrorDiscoverMovies(state: Boolean) =
        movieRepository.setErrorDiscoverMovies(state)
}