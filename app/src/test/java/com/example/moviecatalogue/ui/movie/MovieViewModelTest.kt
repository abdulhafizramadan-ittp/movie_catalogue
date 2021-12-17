package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var movieViewModel: MovieViewModel

    private val dummyDiscoverMovies = ResponseDummy.generateDummyDiscoverMovies()
    private val dummyEmptyDiscoverMovies = ResponseDummy.generateDummyEmptyDiscoverMovies()
    private val dummyErrorDiscoverMovies = MutableLiveData(true)

    @Before
    fun setUp() {
        movieRepository = mockk()
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun discoverMovies() {
        every { movieRepository.listMovies } returns ResponseDummy.generateDummyDiscoverMovies()

        val listMovies = movieViewModel.listMovies
        assertNotNull(listMovies)
        assertEquals(dummyDiscoverMovies.value, listMovies.value)

        verify {
            movieRepository.listMovies
        }
    }

    @Test
    fun emptyDiscoverMovies() {
        every { movieRepository.listMovies } returns dummyEmptyDiscoverMovies

        val listMovies = movieViewModel.listMovies
        assertNotNull(listMovies)
        assertEquals(dummyEmptyDiscoverMovies.value, listMovies.value)

        verify {
            movieRepository.listMovies
        }
    }

    @Test
    fun errorDiscoveringMovies() {
        every { movieRepository.errorDiscoverMovies } returns dummyErrorDiscoverMovies

        val errorDiscoverMovies = movieViewModel.errorDiscoverMovies
        assertNotNull(errorDiscoverMovies)
        assertEquals(dummyErrorDiscoverMovies.value, errorDiscoverMovies.value)

        verify {
            movieRepository.errorDiscoverMovies
        }
    }
}