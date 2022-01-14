package com.example.moviecatalogue.helper.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.ItemSmallBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.ui.movie.OnMovieClickListener

class ItemSmallAdapter(private val movieClickListener: OnMovieClickListener) : RecyclerView.Adapter<ItemSmallAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<Movie>()

    fun setMovies(listMovies: List<Movie>) {
        val diffUtil = ItemDiffUtil(this.listMovies, listMovies)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        this.listMovies.apply {
            clear()
            addAll(listMovies)
        }

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemSmallBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                binding.apply {
                    tvItemSmall.text = movie.title
                    ivItemSmall.loadImage(movie.posterPath)
                }
                itemView.setOnClickListener { movieClickListener.onItemClick(movie) }
            }
        }
    }
}