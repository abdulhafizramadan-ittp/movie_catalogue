package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.domain.Movie
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.detail.movie.MovieDetailFragment
import com.example.moviecatalogue.ui.detail.movie.MovieDetailFragment.Companion.MOVIE_EXTRA
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailFragment
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailFragment.Companion.TV_SHOW_EXTRA

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val args: DetailActivityArgs by navArgs()
    private var movie: Movie? = null
    private var tvShow: TvShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = args.movie
        tvShow = args.tvShow

        val fragmentTransaction = supportFragmentManager
            .beginTransaction()

        if (movie != null) {
            val bundle = Bundle().apply {
                putParcelable(MOVIE_EXTRA, movie)
            }
            fragmentTransaction
                .add(
                    R.id.detail_container,
                    MovieDetailFragment::class.java,
                    bundle
                )
        } else if (tvShow != null) {
            val bundle = Bundle().apply {
                putParcelable(TV_SHOW_EXTRA, tvShow)
            }
            fragmentTransaction
                .add(
                    R.id.detail_container,
                    TvShowDetailFragment::class.java,
                    bundle
                )
        }

        fragmentTransaction
            .commit()
    }
}