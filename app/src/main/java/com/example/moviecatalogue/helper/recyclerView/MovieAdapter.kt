package com.example.moviecatalogue.helper.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.ui.movie.OnMovieClickListener

class MovieAdapter(private val movieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<Movie>()

    @SuppressLint("NotifyDataSetChanged")
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

                ivMoviePoster.loadImage(movie.posterPath)
            }
            itemView.setOnClickListener { movieClickListener.onItemClick(movie) }
        }
    }
}