package com.example.moviecatalogue.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val name: String,
    val voteAverage: Double,
    val id: Int,
    val posterPath: String
) : Parcelable
