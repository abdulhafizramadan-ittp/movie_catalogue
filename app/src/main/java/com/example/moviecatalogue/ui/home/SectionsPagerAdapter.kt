package com.example.moviecatalogue.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviecatalogue.ui.home.HomeActivity.Companion.TAB_TITLES
import com.example.moviecatalogue.ui.movie.MovieFragment
import com.example.moviecatalogue.ui.tvShow.TvShowFragment

class SectionsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment.newInstance()
            else -> TvShowFragment.newInstance()
        }
}