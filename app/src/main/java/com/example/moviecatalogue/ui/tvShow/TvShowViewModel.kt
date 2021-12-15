package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.response.TvShowResponse
import com.example.moviecatalogue.data.response.toDomain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {

    private val _listTvShows = MutableLiveData<List<TvShow>>()
    val listTvShows: LiveData<List<TvShow>> = _listTvShows

    private val _errorDiscoverTvShows = MutableLiveData(false)
    val errorDiscoverTvShows: LiveData<Boolean> = _errorDiscoverTvShows

    fun discoverTvShows() {
        ApiConfig.getInstance().getDiscoverTvShow(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<TvShowResponse?> {
                override fun onResponse(
                    call: Call<TvShowResponse?>,
                    response: Response<TvShowResponse?>
                ) {
                    if (response.body()?.results != null) {
                        _errorDiscoverTvShows.value = false
                        _listTvShows.value = response.body()?.results?.map {
                            it.toDomain()
                        }
                    }
                }

                override fun onFailure(call: Call<TvShowResponse?>, t: Throwable) {
                    _errorDiscoverTvShows.value = true
                }
            })
    }

    fun setErrorDiscoverTvShows(state: Boolean) {
        _errorDiscoverTvShows.value = false
    }
}