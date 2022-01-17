package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.viewHolder.ItemGridViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() =  _binding as FragmentMovieBinding

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        movieViewModel.discoverMovies().observe(viewLifecycleOwner) { listMovies ->
            if (listMovies != null) {
                setupRecyclerView(listMovies)
            }
        }
    }

    private fun setupRecyclerView(listMovies: List<Movie>) {
        val dataSource = dataSourceTypedOf(listMovies)
        binding.rvMovie.setup {
            withDataSource(dataSource)
            withItem<Movie, ItemGridViewHolder>(R.layout.item_grid) {
                onBind(::ItemGridViewHolder) { _, item ->
                    tvItemGrid.text = item.title
                    ivItemGrid.loadImage(item.posterPath)
                }
                onClick {
                    val toDetailMovie = MovieFragmentDirections
                        .actionNavigationMovieToDetailActivity(item, null)
                    findNavController()
                        .navigate(toDetailMovie)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}