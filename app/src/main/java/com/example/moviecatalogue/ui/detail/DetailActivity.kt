package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.detail.movie.MovieDetailFragment
import com.example.moviecatalogue.ui.detail.tvShow.TvShowDetailFragment

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        if (extras != null) {
            val id = extras.getInt(ID)
            val type = extras.getString(DETAIL_TYPE)

            if (type != null) {
                setupFragment(type, id)
            }
        }
    }

    private fun setupFragment(type: String, id: Int) {
        val fragment =
            if (type == TYPE_MOVIE) MovieDetailFragment.newInstance(id) else TvShowDetailFragment.newInstance(id)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    companion object {
        const val ID = "id"
        const val DETAIL_TYPE = "detail_type"

        const val TYPE_MOVIE = "movie_type"
        const val TYPE_TV_SHOW = "tv_show_type"

        private const val TAG = "DetailActivity"
    }
}