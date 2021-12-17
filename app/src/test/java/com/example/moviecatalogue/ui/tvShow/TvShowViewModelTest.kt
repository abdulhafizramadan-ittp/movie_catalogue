package com.example.moviecatalogue.ui.tvShow

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowRepository: TvShowRepository
    private lateinit var tvShowViewModel: TvShowViewModel

    private val dummyDiscoverTvShows = ResponseDummy.generateDummyDiscoverTvShows()
    private val dummyEmptyDiscoverTvShows = ResponseDummy.generateDummyEmptyDiscoverTvShows()
    private val dummyNullDiscoverTvShows = ResponseDummy.generateDummyNullDiscoverTvShows()
    private val dummyErrorDiscoverTvShows = MutableLiveData(true)

    @Before
    fun setUp() {
        tvShowRepository = mockk()
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun discoverTvShows() {
        every { tvShowRepository.discoverTvShows() } answers { }
        every { tvShowRepository.listTvShows } returns dummyDiscoverTvShows

        tvShowViewModel.discoverTvShows()
        val listTvShows = tvShowViewModel.listTvShows.value

        assertNotNull(listTvShows)
        assertEquals(dummyDiscoverTvShows.value, listTvShows)

        verifyAll {
            tvShowRepository.discoverTvShows()
            tvShowRepository.listTvShows
        }
    }

    @Test
    fun emptyDiscoverTvShows() {
        every { tvShowRepository.discoverTvShows() } answers { }
        every { tvShowRepository.listTvShows } returns dummyEmptyDiscoverTvShows

        tvShowViewModel.discoverTvShows()
        val listTvShows = tvShowViewModel.listTvShows.value

        assertNotNull(listTvShows)
        assertTrue(listTvShows?.isEmpty() == true)
        assertEquals(dummyEmptyDiscoverTvShows.value, listTvShows)

        verifyAll {
            tvShowRepository.discoverTvShows()
            tvShowRepository.listTvShows
        }
    }

    @Test
    fun errorDiscoverTvShows() {
        every { tvShowRepository.discoverTvShows() } answers { }
        every { tvShowRepository.listTvShows } returns dummyNullDiscoverTvShows
        every { tvShowRepository.errorDiscoverTvShows } returns dummyErrorDiscoverTvShows

        tvShowViewModel.discoverTvShows()
        val listTvShows = tvShowViewModel.listTvShows.value
        val errorDiscoverTvShows = tvShowViewModel.errorDiscoverTvShows.value

        assertNull(listTvShows)
        assertNotNull(errorDiscoverTvShows)
        assertTrue(errorDiscoverTvShows == true)

        verifyAll {
            tvShowRepository.discoverTvShows()
            tvShowRepository.listTvShows
            tvShowViewModel.errorDiscoverTvShows
        }
    }
}