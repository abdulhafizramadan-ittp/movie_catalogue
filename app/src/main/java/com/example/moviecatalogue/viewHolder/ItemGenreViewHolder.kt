package com.example.moviecatalogue.viewHolder

import android.view.View
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.example.moviecatalogue.R

class ItemGenreViewHolder(itemView: View) : ViewHolder(itemView) {
    val tvItemGenreName: TextView = itemView.findViewById(R.id.chip_genre_name)
}