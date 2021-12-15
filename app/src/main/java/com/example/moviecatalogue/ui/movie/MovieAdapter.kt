package com.example.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.api.ApiConfig.Companion.POSTER_MD
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.ItemsMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<Movie>()

    fun setMovies(listMovies: List<Movie>?) {
        if (listMovies != null) {
            this.listMovies.apply {
                clear()
                addAll(listMovies)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                val movieFooter = "${movie.releaseDate} . ${movie.voteAverage} . ${movie.originalLanguage}"

                tvTvShowTitle.text = movie.title
                tvMovieOverview.text = movie.overview
                tvMovieFooter.text = movieFooter

                val posterUrl = POSTER_MD + movie.posterPath

                Glide.with(itemView.context)
                    .load(posterUrl)
                    .into(ivMoviePoster)
            }
        }
    }
}