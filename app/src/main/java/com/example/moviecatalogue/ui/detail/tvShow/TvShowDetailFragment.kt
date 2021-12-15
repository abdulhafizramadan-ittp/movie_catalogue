package com.example.moviecatalogue.ui.detail.tvShow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.FragmentTvShowDetailBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class TvShowDetailFragment : Fragment() {

    private var _binding: FragmentTvShowDetailBinding? = null
    private val binding: FragmentTvShowDetailBinding get() = _binding as FragmentTvShowDetailBinding

    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel

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

        val tvShowId = arguments?.getInt(TV_SHOW_ID)

        if (tvShowId != null) {
            setupViewModel(tvShowId)
        }

        setupToolbar()
        setupDetailTvShow()
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

    private fun setupViewModel(tvShowId: Int) {
        tvShowDetailViewModel.apply {
            if (tvShowDetail.value == null) {
                getTvShowDetail(tvShowId)
            }
        }
    }

    private fun setupDetailTvShow() {
        tvShowDetailViewModel.tvShowDetail.observe(viewLifecycleOwner) { tvShowDetail ->
            binding.apply {
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