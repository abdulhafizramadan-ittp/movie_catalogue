package com.example.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.Movie
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
class MovieViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieViewModel: MovieViewModel

    private val dummyDiscoverMovies = ResponseDummy.generateDummyDiscoverMovies()
    private val dummyEmptyDiscoverMovies = ResponseDummy.generateDummyEmptyDiscoverMovies()
    private val dummyNullDiscoverMovies = ResponseDummy.generateDummyNullDiscoverMovies()

    @Mock
    private lateinit var discoverMoviesObserver: Observer<List<Movie>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun discoverMovies() {
        `when`(movieRepository.discoverMovies()).thenReturn(dummyDiscoverMovies)

        val listMovies = movieViewModel.discoverMovies().value

        verify(movieRepository).discoverMovies()

        assertNotNull(listMovies)
        assertEquals(dummyDiscoverMovies.value, listMovies)

        movieViewModel.discoverMovies().observeForever(discoverMoviesObserver)
        verify(discoverMoviesObserver).onChanged(dummyDiscoverMovies.value)
    }

    @Test
    fun emptyDiscoverMovies() {
        `when`(movieRepository.discoverMovies()).thenReturn(dummyEmptyDiscoverMovies)

        val listMovies = movieViewModel.discoverMovies().value

        verify(movieRepository).discoverMovies()

        assertNotNull(listMovies)
        assertTrue(listMovies?.isEmpty() == true)
        assertEquals(dummyEmptyDiscoverMovies.value, listMovies)

        movieViewModel.discoverMovies().observeForever(discoverMoviesObserver)
        verify(discoverMoviesObserver).onChanged(dummyEmptyDiscoverMovies.value)
    }

    @Test
    fun errorDiscoveringMovies() {
        `when`(movieRepository.discoverMovies()).thenReturn(dummyNullDiscoverMovies)

        val listMovies = movieViewModel.discoverMovies().value

        verify(movieRepository).discoverMovies()

        assertNull(listMovies)
        assertEquals(dummyNullDiscoverMovies.value, listMovies)
    }
}