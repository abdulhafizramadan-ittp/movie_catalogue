package com.example.moviecatalogue.ui.detail.tvShow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentTvShowDetailBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class TvShowDetailFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentTvShowDetailBinding? = null
    private val binding: FragmentTvShowDetailBinding get() = _binding as FragmentTvShowDetailBinding

    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel
    private var tvShowId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowDetailViewModel = ViewModelProvider(requireActivity())[TvShowDetailViewModel::class.java]

        tvShowId = arguments?.getInt(TV_SHOW_ID)

        tvShowId?.let { setupViewModel(it) }

        binding.swipeToRefresh.setOnRefreshListener(this)

        setupToolbar()
        setupDetailTvShow()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        tvShowId?.let { tvShowDetailViewModel.getTvShowDetail(it) }
    }

    private fun setupToolbar() {
        (activity as DetailActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
    }

    private fun setupViewModel(tvShowId: Int) {
        tvShowDetailViewModel.apply {
            if (tvShowDetail.value == null) {
                getTvShowDetail(tvShowId)
            }

            tvShowDetailError.observe(viewLifecycleOwner) { error ->
                if (error) {
                    binding.swipeToRefresh.isRefreshing = false
                    activity?.apply {
                        Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_network), Snackbar.LENGTH_LONG)
                            .setAction(R.string.try_again) {
                                refreshNetwork()
                            }
                            .show()
                    }
                }
            }
        }
    }

    private fun setupDetailTvShow() {
        tvShowDetailViewModel.tvShowDetail.observe(viewLifecycleOwner) { tvShowDetail ->
            binding.apply {
                binding.swipeToRefresh.isRefreshing = false

                toolbar.title = tvShowDetail.name

                tvShowTitle.text = tvShowDetail.name
                tvShowTagline.text = tvShowDetail.tagline
                tvShowStatus.text = tvShowDetail.status
                tvShowSeason.text = tvShowDetail.numberOfSeasons.toString()
                tvShowRating.text = tvShowDetail.voteAverage.toString()
                tvShowRelease.text = tvShowDetail.firstAirDate
                tvShowLanguage.text = tvShowDetail.originalLanguage
                tvShowSynopsis.text = tvShowDetail.overview

                Glide.with(this@TvShowDetailFragment)
                    .load(tvShowDetail.posterPath)
                    .into(ivTvShowPoster)
            }   
        }
    }

    private fun refreshNetwork() {
        tvShowId?.let {
            tvShowDetailViewModel.getTvShowDetail(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tvShowDetailViewModel.setTvShowDetailError(false)
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