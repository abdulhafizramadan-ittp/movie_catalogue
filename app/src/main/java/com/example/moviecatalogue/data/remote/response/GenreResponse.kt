package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.domain.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null,
)

fun List<GenreResponse>.toDomain(): List<Genre> =
    map { it.toDomain() }

fun GenreResponse.toDomain(): Genre =
    Genre(
        id = id ?: 0,
        name = name ?: ""
    )