package com.example.moviecatalogue.ui.detail.tvShow

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Genre
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.data.domain.toEntity
import com.example.moviecatalogue.databinding.FragmentTvShowDetailBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.ui.HomeActivity
import com.example.moviecatalogue.viewHolder.ItemGenreViewHolder
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailFragment : Fragment() {

    private var _binding: FragmentTvShowDetailBinding? = null
    private val binding: FragmentTvShowDetailBinding get() = _binding as FragmentTvShowDetailBinding

    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModel()
    private val args: TvShowDetailFragmentArgs by navArgs()

    private lateinit var homeActivity: HomeActivity
    private lateinit var tvShow: TvShow

    private var isFavorite = false
    private lateinit var menu: Menu

    private lateinit var parentAppBarLayout: AppBarLayout
    private lateinit var parentBottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeActivity = (activity as HomeActivity)
        tvShow = args.tvShow

        setupToolbar()
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        isTvShowFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> toggleToFavorite()
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        (activity as HomeActivity).apply {
            parentAppBarLayout = findViewById(R.id.appBarLayout)
            parentBottomNavigationView = findViewById(R.id.bottom_navigation)

            parentAppBarLayout.visibility = View.GONE
            parentBottomNavigationView.visibility = View.GONE

            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
    }

    private fun toggleToFavorite() {
        if (isFavorite) {
            tvShowDetailViewModel.deleteFavoriteTvShow(tvShow.toEntity())
            isTvShowFavorite()
        } else {
            tvShowDetailViewModel.insertFavoriteTvShow(tvShow.toEntity())
            isTvShowFavorite()
        }
    }

    private fun setupViewModel() {
        tvShowDetailViewModel.getTvShowDetail(tvShow.id).observe(viewLifecycleOwner) { tvShowDetail ->
            if (tvShowDetail != null) {
                binding.apply {
                    toolbar.title = tvShowDetail.name
                    tvShowTitle.text = tvShowDetail.name
                    tvShowSubtitle.text = tvShowDetail.tagline
                    tvShowStatus.text = tvShowDetail.status
                    tvShowSeason.text = tvShowDetail.numberOfSeasons.toString()
                    tvShowRating.text = tvShowDetail.voteAverage.toString()
                    tvShowSynopsis.text = tvShowDetail.overview

                    ivTvShowPoster.loadImage(tvShowDetail.posterPath)

                    setupGenreRecycler(tvShowDetail.genres)
                }
            }
        }
    }

    private fun setupGenreRecycler(listGenre: List<Genre>) {
        val dataSource = dataSourceTypedOf(listGenre)
        binding.rvGenre.setup {
            withDataSource(dataSource)
            withItem<Genre, ItemGenreViewHolder>(R.layout.item_genre) {
                onBind(::ItemGenreViewHolder) { _, item ->
                    tvItemGenreName.text = item.name
                }
            }
        }
    }

    private fun isTvShowFavorite() {
        tvShowDetailViewModel.getTvShowById(tvShow.id).observe(viewLifecycleOwner) { tvShow ->
            val menuItem = menu.findItem(R.id.action_favorite)
            if (tvShow != null) {
                isFavorite = true
                menuItem.setIcon(R.drawable.ic_favorite)
            } else {
                isFavorite = false
                menuItem.setIcon(R.drawable.ic_nav_favorite)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeActivity.apply {
            parentAppBarLayout.visibility = View.VISIBLE
            parentBottomNavigationView.visibility = View.VISIBLE
        }
        _binding = null
    }
}