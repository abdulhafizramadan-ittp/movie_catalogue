package com.example.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.repository.MovieDetailRepository
import com.example.moviecatalogue.data.viewModelFactory.MovieDetailViewModelFactory
import com.example.moviecatalogue.databinding.FragmentMovieDetailBinding
import com.example.moviecatalogue.helper.loadImage
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class MovieDetailFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding get() = _binding as FragmentMovieDetailBinding

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDetailViewModelFactory = MovieDetailViewModelFactory(MovieDetailRepository())
        movieDetailViewModel = ViewModelProvider(requireActivity(), movieDetailViewModelFactory)[MovieDetailViewModel::class.java]

        movieId = arguments?.getInt(MOVIE_ID)

        movieId?.let { setupViewModel(it) }

        binding.swipeToRefresh.setOnRefreshListener(this)

        setupToolbar()
        setupDetailMovie()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        movieId?.let { movieDetailViewModel.getMovieDetail(it) }
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

    private fun setupDetailMovie() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            if (movieDetail != null) {
                binding.apply {
                    swipeToRefresh.isRefreshing = false

                    toolbar.title = movieDetail.title

                    tvMovieTitle.text = movieDetail.title
                    tvMovieTagline.text = movieDetail.tagline
                    tvMovieStatus.text = movieDetail.status
                    tvMovieRuntime.text = movieDetail.runtime.toString()
                    tvMovieRating.text = movieDetail.voteAverage.toString()
                    tvMovieRelease.text = movieDetail.releaseDate
                    tvMovieLanguage.text = movieDetail.originalLanguage
                    tvMovieSynopsis.text = movieDetail.overview

                    ivMoviePoster.loadImage(movieDetail.posterPath)
                }
            }
        }
    }

    private fun refreshNetwork() {
        movieId?.let {
            movieDetailViewModel.getMovieDetail(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieDetailViewModel.setMovieDetailError(false)
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