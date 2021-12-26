package com.example.moviecatalogue.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.TvShow
import com.example.moviecatalogue.helper.ResponseDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var tvShowViewModel: TvShowViewModel

    private val dummyDiscoverTvShows = ResponseDummy.generateDummyDiscoverTvShows()
    private val dummyEmptyDiscoverTvShows = ResponseDummy.generateDummyEmptyDiscoverTvShows()
    private val dummyNullDiscoverTvShows = ResponseDummy.generateDummyNullDiscoverTvShows()

    @Mock
    private lateinit var discoverTvShowsObserver: Observer<List<TvShow>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun discoverTvShows() {
        `when`(movieRepository.discoverTvShows()).thenReturn(dummyDiscoverTvShows)

        val listTvShows = tvShowViewModel.discoverTvShows().value

        verify(movieRepository).discoverTvShows()

        assertNotNull(listTvShows)
        assertEquals(dummyDiscoverTvShows.value, listTvShows)

        tvShowViewModel.discoverTvShows().observeForever(discoverTvShowsObserver)
        verify(discoverTvShowsObserver).onChanged(dummyDiscoverTvShows.value)
    }

    @Test
    fun emptyDiscoverTvShows() {
        `when`(movieRepository.discoverTvShows()).thenReturn(dummyEmptyDiscoverTvShows)

        val listTvShows = tvShowViewModel.discoverTvShows().value

        verify(movieRepository).discoverTvShows()

        assertNotNull(listTvShows)
        assertEquals(dummyEmptyDiscoverTvShows.value, listTvShows)

        tvShowViewModel.discoverTvShows().observeForever(discoverTvShowsObserver)
        verify(discoverTvShowsObserver).onChanged(dummyEmptyDiscoverTvShows.value)
    }

    @Test
    fun errorDiscoverTvShows() {
        `when`(movieRepository.discoverTvShows()).thenReturn(dummyNullDiscoverTvShows)

        val listTvShows = tvShowViewModel.discoverTvShows().value

        verify(movieRepository).discoverTvShows()

        assertNull(listTvShows)
        assertEquals(dummyNullDiscoverTvShows.value, listTvShows)
    }
}