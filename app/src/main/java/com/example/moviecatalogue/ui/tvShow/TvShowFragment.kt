package com.example.moviecatalogue.ui.tvShow

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
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.helper.extensions.loadImage
import com.example.moviecatalogue.viewHolder.ItemGridViewHolder
import com.example.moviecatalogue.viewHolder.ItemShimmerViewHolder
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

        setupShimmer()

        setupViewModel()
    }

    private fun setupViewModel() {
        tvShowViewModel.discoverTvShows().observe(viewLifecycleOwner) { listTvShows ->
            if (listTvShows != null) {
                binding.shimmerContainer.apply {
                    hideShimmer()
                    visibility = View.GONE
                }
                setupRecyclerView(listTvShows)
            }
        }
    }

    private fun setupShimmer() {
        binding.shimmerContainer.startShimmer()
        val listShimmer = (1..20).toList()
        val dataSource = dataSourceTypedOf(listShimmer)
        binding.rvShimmer.setup {
            withDataSource(dataSource)
            withItem<Int, ItemShimmerViewHolder>(R.layout.shimmer_item_grid) {
                onBind(::ItemShimmerViewHolder) { _, _ -> }
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

                }
                onClick {
                    val toTvShowDetail = TvShowFragmentDirections
                        .actionNavigationTvShowToDetailActivity(null, item)
                    findNavController()
                        .navigate(toTvShowDetail)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}