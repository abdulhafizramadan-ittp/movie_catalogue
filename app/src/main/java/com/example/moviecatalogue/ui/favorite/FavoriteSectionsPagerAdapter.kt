package com.example.moviecatalogue.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviecatalogue.ui.favorite.FavoriteFragment.Companion.FAVORITE_TAB_TITLES

class FavoriteSectionsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int =
        FAVORITE_TAB_TITLES.size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            1 ->
                FavoriteListFragment.newInstance(
                    FavoriteListFragment
                        .Companion
                        .FavoriteType.TvShow()
                )
            else ->
                FavoriteListFragment.newInstance(
                    FavoriteListFragment
                        .Companion
                        .FavoriteType
                        .Movie()
                )
        }
}