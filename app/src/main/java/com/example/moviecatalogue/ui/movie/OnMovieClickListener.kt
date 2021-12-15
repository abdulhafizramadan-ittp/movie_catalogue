package com.example.moviecatalogue.ui.movie

import com.example.moviecatalogue.data.domain.Movie

interface OnMovieClickListener {
    fun onItemClick(movie: Movie)
}