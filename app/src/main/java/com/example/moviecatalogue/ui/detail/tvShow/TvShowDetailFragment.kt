package com.example.moviecatalogue.ui.detail.tvShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Genre
import com.example.moviecatalogue.databinding.FragmentTvShowDetailBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.viewHolder.ItemGenreViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailFragment : Fragment() {

    private var _binding: FragmentTvShowDetailBinding? = null
    private val binding: FragmentTvShowDetailBinding get() = _binding as FragmentTvShowDetailBinding

    private var tvShowId: Int? = null
    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowId = arguments?.getInt(TV_SHOW_ID)

        tvShowId?.let { setupViewModel() }

        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        (activity as DetailActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
    }

    private fun setupViewModel() {
        tvShowId?.let {
            tvShowDetailViewModel.getTvShowDetail(it).observe(viewLifecycleOwner) { tvShowDetail ->
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TV_SHOW_ID = "tv_show_id"

        @JvmStatic
        fun newInstance(tvShowId: Int) =
            TvShowDetailFragment().apply {
                arguments = bundleOf(TV_SHOW_ID to tvShowId)
            }
    }
}