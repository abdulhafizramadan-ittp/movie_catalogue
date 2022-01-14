package com.example.moviecatalogue.helper.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.data.domain.Genre
import com.example.moviecatalogue.databinding.ItemGenreBinding

class ItemGenreAdapter : RecyclerView.Adapter<ItemGenreAdapter.MovieViewHolder>() {

    private val listGenre = ArrayList<Genre>()

    @SuppressLint("NotifyDataSetChanged")
    fun setGenres(listMovies: List<Genre>) {
        this.listGenre.apply {
            clear()
            addAll(listMovies)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size

    inner class MovieViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.apply {
                binding.tvItemGenreName.text = genre.name
            }
        }
    }
}