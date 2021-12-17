package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.response.MovieResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>> = _listMovies

    private val _errorDiscoverMovies = MutableLiveData(false)
    val errorDiscoverMovies: LiveData<Boolean> = _errorDiscoverMovies

    fun discoverMovies() {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getDiscoverMovie(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    _errorDiscoverMovies.value = false
                    val movies = response.body()?.results?.map {
                        it.toDomain()
                    }
                    _listMovies.postValue(movies)
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    _errorDiscoverMovies.value = true
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })
    }

    fun setErrorDiscoverMovies(state: Boolean) {
        _errorDiscoverMovies.value = state
    }
}