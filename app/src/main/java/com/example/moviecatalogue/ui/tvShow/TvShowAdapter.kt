package com.example.moviecatalogue.ui.tvShow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.ItemsTvShowBinding
import com.example.moviecatalogue.helper.loadImage

class TvShowAdapter(private val tvShowClickListener: OnTvShowClickListener) : RecyclerView.Adapter<TvShowAdapter.MovieViewHolder>() {

    private val listTvShows = ArrayList<TvShow>()

    @SuppressLint("NotifyDataSetChanged")
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
        fun bind(tvShow: TvShow) {
            binding.apply {
                val movieFooter = "${tvShow.firstAirDate} . ${tvShow.voteAverage} . ${tvShow.originalLanguage}"

                tvTvShowTitle.text = tvShow.name
                tvTvShowOverview.text = tvShow.overview
                tvTvShowFooter.text = movieFooter

                ivTvShowPoster.loadImage(tvShow.posterPath)

                itemView.setOnClickListener { tvShowClickListener.onItemClick(tvShow) }
            }
        }
    }
}