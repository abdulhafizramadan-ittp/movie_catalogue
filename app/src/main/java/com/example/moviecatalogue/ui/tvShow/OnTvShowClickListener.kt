package com.example.moviecatalogue.ui.tvShow

import com.example.moviecatalogue.data.domain.TvShow

interface OnTvShowClickListener {
    fun onItemClick(tvShow: TvShow)
}