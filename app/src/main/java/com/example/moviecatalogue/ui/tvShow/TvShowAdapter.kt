package com.example.moviecatalogue.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.api.ApiConfig.Companion.POSTER_MD
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.ItemsTvShowBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MovieViewHolder>() {

    private val listTvShows = ArrayList<TvShow>()

    fun setMovies(listShows: List<TvShow>?) {
        if (listShows != null) {
            this.listTvShows.apply {
                clear()
                addAll(listShows)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class MovieViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShow) {
            binding.apply {
                val movieFooter = "${movie.firstAirDate} . ${movie.voteAverage} . ${movie.originalLanguage}"

                tvTvShowTitle.text = movie.name
                tvTvShowOverview.text = movie.overview
                tvTvShowFooter.text = movieFooter

                val posterUrl = POSTER_MD + movie.posterPath

                Glide.with(itemView.context)
                    .load(posterUrl)
                    .into(ivTvShowPoster)
            }
        }
    }
}