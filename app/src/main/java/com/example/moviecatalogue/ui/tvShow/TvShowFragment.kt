package com.example.moviecatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.viewHolder.ItemGridViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding: FragmentTvShowBinding get() = _binding as FragmentTvShowBinding

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        tvShowViewModel.discoverTvShows().observe(viewLifecycleOwner) { listTvShows ->
            if (listTvShows != null) {
                setupRecyclerView(listTvShows)
            }
        }
    }

    private fun setupRecyclerView(listTvShows: List<TvShow>) {
        val dataSource = dataSourceTypedOf(listTvShows)
        binding.rvTvShow.setup {
            withDataSource(dataSource)
            withItem<TvShow, ItemGridViewHolder>(R.layout.item_grid) {
                onBind(::ItemGridViewHolder) { _, item ->
                    tvItemGrid.text = item.name
                    ivItemGrid.loadImage(item.posterPath)

                    onClick {
                        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                            putExtra(DetailActivity.DETAIL_TYPE, DetailActivity.TYPE_TV_SHOW)
                            putExtra(DetailActivity.ID, item.id)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}