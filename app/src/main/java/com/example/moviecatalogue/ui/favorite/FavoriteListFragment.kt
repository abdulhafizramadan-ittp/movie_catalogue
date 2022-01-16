package com.example.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.local.entity.toDomain
import com.example.moviecatalogue.databinding.FragmentFavoriteListBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.viewHolder.ItemFavoriteViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteListFragment : Fragment() {

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding: FragmentFavoriteListBinding
        get() = _binding as FragmentFavoriteListBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getString(FAVORITE_TYPE)

        if (type != null) {
            setupViewModel(type)
        }
    }

    private fun setupViewModel(type: String) {
        when (type) {
            FavoriteType.Movie().type -> {
                favoriteViewModel.getAllFavoriteMovies().observe(viewLifecycleOwner) { listMovieEntities ->
                    if (listMovieEntities != null) {
                        val listMovies = listMovieEntities.toDomain()
                        val dataSource = dataSourceTypedOf(listMovies.toList())
                        binding.rvFavoriteList.setup {
                            withDataSource(dataSource)
                            withItem<Movie, ItemFavoriteViewHolder>(R.layout.item_favorite) {
                                onBind(::ItemFavoriteViewHolder) { _, item ->
                                    tvFavorite.text = item.title
                                    ivFavorite.loadImage(item.posterPath)
                                }
                                onClick {
                                    val toMovieDetail = FavoriteFragmentDirections
                                        .actionNavigationFavoriteToMovieDetailFragment(item)
                                    findNavController()
                                        .navigate(toMovieDetail)
                                }
                            }
                        }
                    }
                }
            }
            FavoriteType.TvShow().type -> {
                favoriteViewModel.getAllFavoriteTvShows().observe(viewLifecycleOwner) { listTvShowsEntities ->
                    if (listTvShowsEntities != null) {
                        val listTvShows = listTvShowsEntities.toDomain()
                        val dataSource = dataSourceTypedOf(listTvShows.toList()).apply {
                            set(
                                newItems = listTvShows,
                                areTheSame = ::areItemsTheSame,
                                areContentsTheSame = ::areItemContentsTheSame
                            )
                        }
                        binding.rvFavoriteList.setup {
                            withDataSource(dataSource)
                            withItem<TvShow, ItemFavoriteViewHolder>(R.layout.item_favorite) {
                                onBind(::ItemFavoriteViewHolder) { _, item ->
                                    tvFavorite.text = item.name
                                    ivFavorite.loadImage(item.posterPath)
                                }
                                onClick {
                                    val toTvShowDetail = FavoriteFragmentDirections
                                        .actionNavigationFavoriteToTvShowDetailFragment(item)
                                    findNavController()
                                        .navigate(toTvShowDetail)
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val FAVORITE_TYPE = "favorite_type"

        @JvmStatic
        fun newInstance(type: FavoriteType) =
            FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putString(FAVORITE_TYPE, type.type)
                }
            }

        private fun <T> areItemsTheSame(left: T, right: T): Boolean {
            return when (left) {
                is Movie -> {
                    right is Movie && right.id == left.id
                }
                is TvShow -> {
                    right is TvShow && right.id == left.id
                }
                else -> false
            }
        }

        private fun <T> areItemContentsTheSame(left: T, right: T): Boolean {
            return when (left) {
                is Movie -> {
                    right is Movie &&
                            right.title == left.title &&
                            right.posterPath == left.posterPath
                }
                is TvShow -> {
                    right is TvShow &&
                            right.name == left.name &&
                            right.posterPath == left.posterPath
                }
                else -> false
            }
        }

        sealed class FavoriteType(val type: String) {
            class Movie : FavoriteType("movie")
            class TvShow : FavoriteType("tv_show")
        }
    }
}