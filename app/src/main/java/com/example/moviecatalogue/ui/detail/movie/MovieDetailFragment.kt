package com.example.moviecatalogue.ui.detail.movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Genre
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.toEntity
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

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    private lateinit var menu: Menu

    private var isFavorite = false
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getParcelable(MOVIE_EXTRA)

        setupToolbar()

        movie?.let {
            setupViewModel()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        isMovieInFavorite()
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
        (activity as DetailActivity).apply {
            supportActionBar?.hide()
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }
    }

    private fun toggleToFavorite() {
        if (isFavorite) {
            movie?.let { movieDetailViewModel.deleteFavoriteMovie(it.toEntity()) }
            isMovieInFavorite()
        } else {
            movie?.let { movieDetailViewModel.insertFavoriteMovie(it.toEntity()) }
            isMovieInFavorite()
        }
    }

    private fun setupViewModel() {
        movie?.let {
            movieDetailViewModel.getMovieDetail(it.id).observe(viewLifecycleOwner) { movieDetail ->
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

    private fun isMovieInFavorite() {
        movie?.let {
            movieDetailViewModel.getMovieById(it.id).observe(viewLifecycleOwner) { movie ->
                val menuItem = menu.findItem(R.id.action_favorite)
                if (movie != null) {
                    isFavorite = true
                    menuItem.setIcon(R.drawable.ic_favorite)
                } else {
                    isFavorite = false
                    menuItem.setIcon(R.drawable.ic_nav_favorite)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MOVIE_EXTRA = "movie_extra"
    }
}