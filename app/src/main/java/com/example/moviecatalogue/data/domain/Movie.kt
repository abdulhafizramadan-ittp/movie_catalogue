package com.example.moviecatalogue.data.domain

import android.os.Parcelable
import com.example.moviecatalogue.data.local.entity.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String
) : Parcelable

fun Movie.toEntity(): MovieEntity =
    MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath
    )
