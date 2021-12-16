package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.data.viewModelFactory.MovieViewModelFactory
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class MovieFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnMovieClickListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() =  _binding as FragmentMovieBinding

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieViewModelFactory = MovieViewModelFactory(MovieRepository())
        movieViewModel = ViewModelProvider(this, movieViewModelFactory)[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(this)

        binding.swipeToRefresh.setOnRefreshListener(this)

        setupViewModel()
        setupRecyclerView()
    }

    override fun onRefresh() {
        movieViewModel.discoverMovies()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DETAIL_TYPE, DetailActivity.TYPE_MOVIE)
            putExtra(DetailActivity.ID, movie.id)
        }
        startActivity(intent)
    }

    private fun setupViewModel() {
        movieViewModel.apply {
            if (listMovies.value == null) {
                movieViewModel.discoverMovies()
            }

            listMovies.observe(viewLifecycleOwner) { listMovies ->
                if (listMovies != null) {
                    binding.apply {
                        swipeToRefresh.isRefreshing = false
                        progressCircular.visibility = View.INVISIBLE
                        lottieError.visibility = View.INVISIBLE
                    }
                    movieAdapter.setMovies(listMovies)
                }
            }

            errorDiscoverMovies.observe(viewLifecycleOwner) { error ->
                if (error) {
                    showErrorNetwork()
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

    private fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun refreshNetwork() {
        binding.apply {
            lottieError.visibility = View.INVISIBLE
            progressCircular.visibility = View.VISIBLE
        }
        Handler(Looper.getMainLooper()).postDelayed({
            movieViewModel.discoverMovies()
        }, 500)
    }

    private fun showErrorNetwork() {
        binding.apply {
            progressCircular.visibility = View.INVISIBLE
            lottieError.visibility =
                if (movieViewModel.listMovies.value == null) View.VISIBLE else View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieViewModel.setErrorDiscoverMovies(false)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MovieFragment()
    }
}