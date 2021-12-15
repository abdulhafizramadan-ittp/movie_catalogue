package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.google.android.material.snackbar.Snackbar

class MovieFragment : Fragment() {

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

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieAdapter = MovieAdapter()

        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        movieViewModel.apply {
            if (listMovies.value == null) {
                movieViewModel.discoverMovies()
            }

            listMovies.observe(viewLifecycleOwner) { listMovies ->
                if (listMovies != null) {
                    binding.progressCircular.visibility = View.INVISIBLE
                    movieAdapter.setMovies(listMovies)
                }
            }

            errorDiscoverMovies.observe(viewLifecycleOwner) { error ->
                if (error != null && error) {
                    showErrorNetwork()
                    Snackbar.make(requireContext(), requireView(), getString(R.string.error_network), Snackbar.LENGTH_LONG)
                        .show()
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

    private fun showErrorNetwork() {
        binding.apply {
            progressCircular.visibility = View.INVISIBLE
            rvMovie.visibility = View.INVISIBLE
            lottieError.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MovieFragment()
    }
}