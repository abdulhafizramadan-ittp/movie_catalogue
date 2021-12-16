package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.ResponseDummy
import com.example.moviecatalogue.ui.movie.MovieViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowRepository: TvShowRepository
    private lateinit var tvShowViewModel: TvShowViewModel
    private val dummyDiscoverTvShowsResponse = ResponseDummy.generateDummyDiscoverTvShows()
    private val dummyEmptyDiscoverTvShowsResponse = ResponseDummy.generateDummyEmptyDiscoverTvShows()
    private val dummyErrorDiscoverTvShows = MutableLiveData(true)

    @Before
    fun setUp() {
        tvShowRepository = mockk()
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun discoverTvShows() {
        every { tvShowRepository.listTvShows } returns dummyDiscoverTvShowsResponse

        val listTvShows = tvShowViewModel.listTvShows
        assertNotNull(listTvShows)
        assertEquals(dummyDiscoverTvShowsResponse.value, listTvShows.value)

        verify {
            tvShowRepository.listTvShows
        }
    }

    @Test
    fun emptyDiscoverTvShows() {
        every { tvShowRepository.listTvShows } returns dummyEmptyDiscoverTvShowsResponse

        val listTvShows = tvShowViewModel.listTvShows
        assertNotNull(listTvShows)
        assertEquals(dummyEmptyDiscoverTvShowsResponse.value, listTvShows.value)

        verify {
            tvShowRepository.listTvShows
        }
    }

    @Test
    fun errorDiscoverTvShows() {
        every { tvShowRepository.errorDiscoverTvShows } returns dummyErrorDiscoverTvShows

        val errorDiscoverTvShows = tvShowViewModel.errorDiscoverTvShows
        assertNotNull(errorDiscoverTvShows)
        assertEquals(dummyErrorDiscoverTvShows.value, errorDiscoverTvShows.value)

        verify {
            tvShowViewModel.errorDiscoverTvShows
        }
    }
}