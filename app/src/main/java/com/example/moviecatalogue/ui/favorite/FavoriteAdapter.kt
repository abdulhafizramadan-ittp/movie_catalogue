package com.example.moviecatalogue.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.databinding.TestBinding
import com.example.moviecatalogue.helper.recyclerView.ItemSmallAdapter

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val listTitles = arrayListOf<String>()

    fun setTitles(listTitles: List<String>) {
        this.listTitles.apply {
            clear()
            addAll(listTitles)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listTitles[position])

    override fun getItemCount(): Int =
        listTitles.size

    inner class ViewHolder(private val binding: TestBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(title: String) {
                binding.tvItemTestName.text = title
                binding.rvTestItem
            }
        }
}