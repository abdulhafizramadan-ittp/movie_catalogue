package com.example.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.helper.recyclerView.ItemGridAdapter
import com.example.moviecatalogue.helper.recyclerView.ItemSmallAdapter
import com.example.moviecatalogue.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), OnMovieClickListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() =  _binding as FragmentMovieBinding

    private val movieViewModel: MovieViewModel by viewModel()

    private lateinit var moviePopularAdapter: ItemSmallAdapter
    private lateinit var movieTrendAdapter: ItemSmallAdapter
    private lateinit var movieFreeAdapter: ItemGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePopularAdapter = ItemSmallAdapter(this)
        movieTrendAdapter = ItemSmallAdapter(this)
        movieFreeAdapter = ItemGridAdapter(this)

        setupViewModel()
        setupRecyclerView()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DETAIL_TYPE, DetailActivity.TYPE_MOVIE)
            putExtra(DetailActivity.ID, movie.id)
        }
        startActivity(intent)
    }

    private fun setupViewModel() {
        movieViewModel.discoverMovies().observe(viewLifecycleOwner) { listMovies ->
            if (listMovies != null) {
                moviePopularAdapter.setMovies(listMovies)
                movieTrendAdapter.setMovies(listMovies)
                movieFreeAdapter.setMovies(listMovies)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvPopular.apply {
                setHasFixedSize(true)
                adapter = moviePopularAdapter
            }
            rvTrend.apply {
                setHasFixedSize(true)
                adapter = movieTrendAdapter
            }
            rvFreeToWatch.apply {
                setHasFixedSize(true)
                adapter = movieFreeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}