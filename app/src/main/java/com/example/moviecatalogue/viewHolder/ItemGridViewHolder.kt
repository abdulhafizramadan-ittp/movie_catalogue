package com.example.moviecatalogue.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.recyclical.ViewHolder
import com.example.moviecatalogue.R

class ItemGridViewHolder(itemView: View) : ViewHolder(itemView) {
    val ivItemGrid: ImageView = itemView.findViewById(R.id.iv_item_grid)
    val tvItemGrid: TextView = itemView.findViewById(R.id.tv_item_grid)
}