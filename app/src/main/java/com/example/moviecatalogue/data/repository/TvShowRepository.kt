package com.example.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.response.TvShowResponse
import com.example.moviecatalogue.data.response.toDomain
import com.example.moviecatalogue.helper.EspressoIdlingResource
import com.example.moviecatalogue.helper.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository {
    private val _listTvShows = MutableLiveData<List<TvShow>>()
    val listTvShows: LiveData<List<TvShow>> = _listTvShows

    private val _errorDiscoverTvShows = MutableLiveData<SingleEvent<Boolean>>()
    val errorDiscoverTvShows: LiveData<SingleEvent<Boolean>> = _errorDiscoverTvShows

    fun discoverTvShows() {
        EspressoIdlingResource.increment()
        ApiConfig.getInstance().getDiscoverTvShow(ApiConfig.TMDB_TOKEN)
            .enqueue(object : Callback<TvShowResponse?> {
                override fun onResponse(
                    call: Call<TvShowResponse?>,
                    response: Response<TvShowResponse?>
                ) {
                    if (response.body()?.results != null) {
                        _errorDiscoverTvShows.value = SingleEvent(false)
                        _listTvShows.value = response.body()?.results?.map {
                            it.toDomain()
                        }
                    }
                    EspressoIdlingResource.ifNotIdlingDecrement()
                }

                override fun onFailure(call: Call<TvShowResponse?>, t: Throwable) {
                    _errorDiscoverTvShows.value = SingleEvent(true)
                    EspressoIdlingResource.decrement()
                }
            })
    }
}