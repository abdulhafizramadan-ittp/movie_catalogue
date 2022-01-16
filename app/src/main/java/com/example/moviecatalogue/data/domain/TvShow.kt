package com.example.moviecatalogue.data.domain

import android.os.Parcelable
import com.example.moviecatalogue.data.local.entity.TvShowEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val posterPath: String
) : Parcelable

fun TvShow.toEntity(): TvShowEntity =
    TvShowEntity(
        id = id,
        name = name,
        posterPath = posterPath
    )
