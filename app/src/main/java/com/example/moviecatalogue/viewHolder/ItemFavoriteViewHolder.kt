package com.example.moviecatalogue.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.example.moviecatalogue.R

class ItemFavoriteViewHolder(view: View) : ViewHolder(view) {
    val ivFavorite: ImageView = view.findViewById(R.id.iv_favorite)
    val tvFavorite: TextView = view.findViewById(R.id.tv_favorite)
}