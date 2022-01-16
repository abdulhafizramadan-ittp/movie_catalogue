package com.example.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviecatalogue.data.domain.Movie

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String
)

fun List<MovieEntity>.toDomain(): List<Movie> =
    map { it.toDomain() }

fun MovieEntity.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        posterPath = posterPath
    )
