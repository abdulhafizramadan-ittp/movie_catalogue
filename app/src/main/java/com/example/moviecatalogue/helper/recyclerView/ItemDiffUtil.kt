package com.example.moviecatalogue.helper.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.moviecatalogue.data.domain.Movie

class ItemDiffUtil(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return (
            oldItem.title == newItem.title &&
            oldItem.posterPath == newItem.posterPath
        )
    }
}