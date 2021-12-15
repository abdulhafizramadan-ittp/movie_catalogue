package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.api.ApiService
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.response.MovieResponse
import com.example.moviecatalogue.data.response.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>> = _listMovies

    private val _errorDiscoverMovies = MutableLiveData(false)
    val errorDiscoverMovies: LiveData<Boolean> = _errorDiscoverMovies

    fun discoverMovies() {
        ApiConfig.getInstance().getDiscoverMovie(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    if (response.body()?.results != null) {
                        _errorDiscoverMovies.value = false
                        _listMovies.value = response.body()?.results?.map {
                            it.toDomain()
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    _errorDiscoverMovies.value = true
                }
            })
    }

    fun setErrorDiscoverMovies(state: Boolean) {
        _errorDiscoverMovies.value = state
    }
}