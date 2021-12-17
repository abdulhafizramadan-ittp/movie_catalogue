package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.MovieRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var movieViewModel: MovieViewModel

    private val dummyDiscoverMovies = ResponseDummy.generateDummyDiscoverMovies()
    private val dummyEmptyDiscoverMovies = ResponseDummy.generateDummyEmptyDiscoverMovies()
    private val dummyNullDiscoverMovies = ResponseDummy.generateDummyNullDiscoverMovies()
    private val dummyErrorDiscoverMovies = MutableLiveData(true)

    @Before
    fun setUp() {
        movieRepository = mockk()
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun discoverMovies() {
        every { movieRepository.discoverMovies() } answers { }
        every { movieRepository.listMovies } returns ResponseDummy.generateDummyDiscoverMovies()

        movieViewModel.discoverMovies()
        val listMovies = movieViewModel.listMovies.value

        assertNotNull(listMovies)
        assertEquals(dummyDiscoverMovies.value, listMovies)

        verifyAll{
            movieRepository.discoverMovies()
            movieRepository.listMovies
        }
    }

    @Test
    fun emptyDiscoverMovies() {
        every { movieRepository.discoverMovies() } answers { }
        every { movieRepository.listMovies } returns dummyEmptyDiscoverMovies

        movieViewModel.discoverMovies()
        val listMovies = movieViewModel.listMovies.value

        assertNotNull(listMovies)
        assertTrue(listMovies?.isEmpty() == true)
        assertEquals(dummyEmptyDiscoverMovies.value, listMovies)

        verifyAll {
            movieRepository.discoverMovies()
            movieRepository.listMovies
        }
    }

    @Test
    fun errorDiscoveringMovies() {
        every { movieRepository.discoverMovies() } answers { }
        every { movieRepository.listMovies } returns dummyNullDiscoverMovies
        every { movieRepository.errorDiscoverMovies } returns dummyErrorDiscoverMovies

        movieViewModel.discoverMovies()
        val listMovies = movieViewModel.listMovies.value
        val errorDiscoverMovies = movieViewModel.errorDiscoverMovies.value

        assertNull(listMovies)
        assertNotNull(errorDiscoverMovies)
        assertTrue(errorDiscoverMovies == true)

        verify {
            movieRepository.discoverMovies()
            movieRepository.listMovies
            movieRepository.errorDiscoverMovies
        }
    }
}