package com.example.moviecatalogue.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.TvShow

object ResponseDummy {

    fun generateDummyDiscoverMovies(): LiveData<List<Movie>> {
        val listMovies = arrayListOf<Movie>()
        for (movie in 1..20) {
            listMovies.add(
                Movie("","","",0.0,0, "","")
            )
        }
        return MutableLiveData(listMovies)
    }

    fun generateDummyEmptyDiscoverMovies(): LiveData<List<Movie>> {
        return MutableLiveData()
    }

    fun generateDummyDiscoverTvShows(): LiveData<List<TvShow>> {
        val listTvShows = arrayListOf<TvShow>()
        for (movie in 1..20) {
            listTvShows.add(
                TvShow("", "","","", 0.0, 0, "")
            )
        }
        return MutableLiveData(listTvShows)
    }

    fun generateDummyEmptyDiscoverTvShows(): LiveData<List<TvShow>> {
        return MutableLiveData()
    }
}