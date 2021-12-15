package com.example.moviecatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.google.android.material.snackbar.Snackbar

class TvShowFragment : Fragment(), OnTvShowClickListener {

    private var _binding: FragmentTvShowBinding? = null
    private val binding: FragmentTvShowBinding get() = _binding as FragmentTvShowBinding

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowViewModel = ViewModelProvider(this)[TvShowViewModel::class.java]
        tvShowAdapter = TvShowAdapter(this)

        setupViewModel()
        setupRecyclerView()
    }

    override fun onItemClick(tvShow: TvShow) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DETAIL_TYPE, DetailActivity.TYPE_TV_SHOW)
            putExtra(DetailActivity.ID, tvShow.id)
        }
        startActivity(intent)
    }

    private fun setupViewModel() {
        tvShowViewModel.apply {
            if (listTvShows.value == null) {
                discoverTvShows()
            }

            listTvShows.observe(viewLifecycleOwner) { listTvShows ->
                if (listTvShows != null) {
                    binding.progressCircular.visibility = View.INVISIBLE
                    tvShowAdapter.setMovies(listTvShows)
                }
            }

            errorDiscoverTvShows.observe(viewLifecycleOwner) { error ->
                if (error != null && error) {
                    showErrorNetwork()
                    Snackbar.make(requireContext(), requireView(), getString(R.string.error_network), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        binding.rvTvShow.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun showErrorNetwork() {
        binding.apply {
            progressCircular.visibility = View.INVISIBLE
            rvTvShow.visibility = View.INVISIBLE
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
            TvShowFragment()
    }
}