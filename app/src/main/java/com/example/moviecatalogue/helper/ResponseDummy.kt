package com.example.moviecatalogue.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.TvShowDetail

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

    fun generateDummyEmptyDiscoverMovies(): LiveData<List<Movie>> =
        MutableLiveData(emptyList())

    fun generateDummyNullDiscoverMovies(): LiveData<List<Movie>> =
        MutableLiveData()

    fun generateDummyDiscoverTvShows(): LiveData<List<TvShow>> {
        val listTvShows = arrayListOf<TvShow>()
        for (movie in 1..20) {
            listTvShows.add(
                TvShow("", "","","", 0.0, 0, "")
            )
        }
        return MutableLiveData(listTvShows)
    }

    fun generateDummyEmptyDiscoverTvShows(): LiveData<List<TvShow>> =
        MutableLiveData(emptyList())

    fun generateDummyNullDiscoverTvShows(): LiveData<List<TvShow>> =
        MutableLiveData()

    fun generateDummyMovieDetail(): LiveData<MovieDetail> =
        MutableLiveData(
            MovieDetail(
                overview="Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                originalLanguage="en",
                releaseDate="2021-12-15",
                voteAverage=8.8,
                runtime=148,
                id=634649,
                title="Spider-Man: No Way Home",
                tagline="The Multiverse unleashed.",
                posterPath="http://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                status="Released"
            )
        )

    fun generateDummyEmptyMovieDetail(): LiveData<MovieDetail> =
        MutableLiveData(
            MovieDetail("", "", "", 0.0, 0, 0, "", "", "", "")
        )

    fun generateDummyNullMovieDetail(): LiveData<MovieDetail> =
        MutableLiveData()

    fun generateDummyTvShowDetail(): LiveData<TvShowDetail> =
        MutableLiveData(
            TvShowDetail(
                firstAirDate="2021-11-18",
                overview="Follow Moiraine, a member of the shadowy and influential all-female organization called the “Aes Sedai” as she embarks on a dangerous, world-spanning journey with five young men and women. Moiraine believes one of them might be the reincarnation of an incredibly powerful individual, whom prophecies say will either save humanity or destroy it.",
                originalLanguage="en",
                type="Scripted",
                posterPath="http://image.tmdb.org/t/p/w500/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg",
                voteAverage=8.1,
                name="The Wheel of Time",
                tagline="nana",
                id=71914,
                numberOfSeasons=1,
                lastAirDate="2021-12-09",
                status="Returning Series",

            )
        )

    fun generateDummyEmptyTvShowDetail(): LiveData<TvShowDetail> =
        MutableLiveData(
            TvShowDetail("", "", "", "", "", 0.0, "", "", 0, 0, "", "")
        )

    fun generateDummyNullTvShowDetail(): LiveData<TvShowDetail> =
        MutableLiveData()
}