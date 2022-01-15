package com.example.moviecatalogue.ui.detail.movie

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
import com.example.moviecatalogue.databinding.FragmentMovieDetailBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.helper.extensions.runtimeToHour
import com.example.moviecatalogue.helper.extensions.runtimeToMinute
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.viewHolder.ItemGenreViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding get() = _binding as FragmentMovieDetailBinding

    private var movieId: Int? = null
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt(MOVIE_ID)

        movieId?.let {
            setupViewModel()
        }

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
        movieId?.let {
            movieDetailViewModel.getMovieDetail(it).observe(viewLifecycleOwner) { movieDetail ->
                if (movieDetail != null) {
                    binding.apply {
                        val movieRuntime = getString(
                            R.string.movie_runtime,
                            movieDetail.runtime.runtimeToHour(),
                            movieDetail.runtime.runtimeToMinute()
                        )

                        toolbar.title = movieDetail.title
                        tvMovieTitle.text = movieDetail.title
                        tvMovieSubtitle.text = movieDetail.tagline
                        tvMovieStatus.text = movieDetail.status
                        tvMovieRuntime.text = movieRuntime
                        tvMovieRating.text = movieDetail.voteAverage.toString()
                        tvMovieSynopsis.text = movieDetail.overview

                        ivMoviePoster.loadImage(movieDetail.posterPath)

                        setupGenreRecycler(movieDetail.genres)
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
        private const val MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = bundleOf(MOVIE_ID to movieId)
            }
    }
}