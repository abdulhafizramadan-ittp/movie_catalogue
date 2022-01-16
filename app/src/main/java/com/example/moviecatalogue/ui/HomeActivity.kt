package com.example.moviecatalogue.ui

import android.os.Bundle
import android.view.Menu
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityHomeBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    lateinit var appBarLayout: AppBarLayout
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBarLayout = binding.appBarLayout
        bottomNavigation = binding.bottomNavigation

        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigation.setupWithNavController(navController)

//        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
//        binding.viewPager.adapter = sectionsPagerAdapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = getString(TAB_TITLES[position])
//        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }
}