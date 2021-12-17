package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowRepository: TvShowRepository
    private lateinit var tvShowViewModel: TvShowViewModel

    private val dummyDiscoverTvShows = ResponseDummy.generateDummyDiscoverTvShows()
    private val dummyEmptyDiscoverTvShows = ResponseDummy.generateDummyEmptyDiscoverTvShows()
    private val dummyErrorDiscoverTvShows = MutableLiveData(true)

    @Before
    fun setUp() {
        tvShowRepository = mockk()
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun discoverTvShows() {
        every { tvShowRepository.listTvShows } returns dummyDiscoverTvShows

        val listTvShows = tvShowViewModel.listTvShows
        assertNotNull(listTvShows)
        assertEquals(dummyDiscoverTvShows.value, listTvShows.value)

        verify {
            tvShowRepository.listTvShows
        }
    }

    @Test
    fun emptyDiscoverTvShows() {
        every { tvShowRepository.listTvShows } returns dummyEmptyDiscoverTvShows

        val listTvShows = tvShowViewModel.listTvShows
        assertNotNull(listTvShows)
        assertEquals(dummyEmptyDiscoverTvShows.value, listTvShows.value)

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