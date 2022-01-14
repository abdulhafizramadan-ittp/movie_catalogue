package com.example.moviecatalogue.data.remote.response

import com.example.moviecatalogue.data.domain.Cast
import com.example.moviecatalogue.data.remote.api.ApiConfig
import com.google.gson.annotations.SerializedName

data class CastResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class CastItem(

	@field:SerializedName("character")
	val character: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

fun CastItem.toDomain(): Cast =
	Cast(
		character = character ?: "",
		name = name ?: "",
		profilePath = ApiConfig.POSTER_MD + profilePath,
		id = id ?: 0
	)