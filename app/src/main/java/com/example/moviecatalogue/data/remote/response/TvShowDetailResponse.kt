package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.domain.TvShowDetail
import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int? = null,

	@field:SerializedName("last_air_date")
	val lastAirDate: String? = null,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

fun TvShowDetailResponse.toDomain(): TvShowDetail =
	TvShowDetail(
		firstAirDate = firstAirDate ?: "-",
		overview = overview ?: "-",
		originalLanguage = originalLanguage ?: "-",
		type = type ?: "-",
		posterPath = ApiConfig.POSTER_MD + posterPath,
		voteAverage = voteAverage ?: 0.0,
		name = name ?: "-",
		tagline = tagline ?: "-",
		id = id ?: 0,
		numberOfSeasons = numberOfSeasons ?: 0,
		lastAirDate = lastAirDate ?: "-",
		status = status ?: "-"
	)
