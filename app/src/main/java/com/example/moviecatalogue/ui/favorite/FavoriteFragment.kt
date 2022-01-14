package com.example.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.databinding.FragmentFavoriteBinding
import com.example.moviecatalogue.helper.recyclerView.ItemSmallAdapter
import com.example.moviecatalogue.ui.movie.OnMovieClickListener

class FavoriteFragment : Fragment(), OnMovieClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding as FragmentFavoriteBinding

    private lateinit var smallAdapter: ItemSmallAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        smallAdapter = ItemSmallAdapter(this)

        val listSmall = arrayListOf<Movie>()

        for (i in 1..20) {
            listSmall.add(
                Movie(
                    "Over $i",
                    "Original $i",
                    "Release $i",
                    i.toDouble(),
                    i,
                    "Title $i",
                    ""
                )
            )
        }

        smallAdapter.setMovies(listSmall)

        binding.rvTest.adapter = smallAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: Movie) {

    }
}