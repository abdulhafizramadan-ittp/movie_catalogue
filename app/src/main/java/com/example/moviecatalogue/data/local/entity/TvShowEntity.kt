package com.example.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecatalogue.data.domain.TvShow

@Entity
data class TvShowEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String
)

fun List<TvShowEntity>.toDomain(): List<TvShow> =
    map { it.toDomain() }

fun TvShowEntity.toDomain(): TvShow =
    TvShow(
        id = id,
        name = name,
        posterPath = posterPath
    )
