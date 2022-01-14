package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.example.moviecatalogue.data.domain.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenreResponse>? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("runtime")
	val runtime: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

fun MovieDetailResponse.toDomain(): MovieDetail =
	MovieDetail(
		overview = overview ?: "-",
		originalLanguage = originalLanguage ?: "-",
		releaseDate = releaseDate ?: "-",
		genres = genres?.toDomain() ?: emptyList(),
		voteAverage = voteAverage ?: 0.0,
		runtime = runtime ?: 0,
		id = id ?: 0,
		title = title ?: "-",
		tagline = tagline ?: "-",
		posterPath = ApiConfig.POSTER_MD + posterPath,
		status = status ?: "-"
	)
