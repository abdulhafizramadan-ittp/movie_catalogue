package com.example.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentMovieDetailBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding get() = _binding as FragmentMovieDetailBinding

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        val movieId = arguments?.getInt(MOVIE_ID)

        if (movieId != null) {
            setupViewModel(movieId)
        }

        setupToolbar()
        setupDetailMovie()
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

    private fun setupViewModel(movieId: Int) {
        movieDetailViewModel.apply {
            if (movieDetail.value == null) {
                getMovieDetail(movieId)
            }

            movieDetailError.observe(viewLifecycleOwner) { error ->
                if (error) {
                    Snackbar.make(requireContext(), requireView(), getString(R.string.error_network), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun setupDetailMovie() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            if (movieDetail != null)
            binding.apply {
                toolbar.title = movieDetail.title

                tvMovieTitle.text = movieDetail.title
                tvMovieTagline.text = movieDetail.tagline
                tvMovieStatus.text = movieDetail.status
                tvMovieRuntime.text = movieDetail.runtime.toString()
                tvMovieRating.text = movieDetail.voteAverage.toString()
                tvMovieRelease.text = movieDetail.releaseDate
                tvMovieLanguage.text = movieDetail.originalLanguage
                tvMovieSynopsis.text = movieDetail.overview

                Glide.with(this@MovieDetailFragment)
                    .load(movieDetail.posterPath)
                    .into(ivMoviePoster)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = bundleOf(MOVIE_ID to movieId)
            }
    }
}