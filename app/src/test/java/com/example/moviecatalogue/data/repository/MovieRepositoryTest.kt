package com.example.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.helper.ResponseDummy
import com.example.moviecatalogue.helper.viewModel.SingleEvent
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieRepositoryTest {

    @MockK
    private lateinit var movieRepository: MovieRepository

    private val dummyDiscoverMovies = ResponseDummy.generateDummyDiscoverMovies()
    private val dummyEmptyDiscoverMovies = ResponseDummy.generateDummyEmptyDiscoverMovies()
    private val dummyNullDiscoverMovies = ResponseDummy.generateDummyNullDiscoverMovies()
    private val dummyErrorDiscoverMovies = MutableLiveData(SingleEvent(true))

    private val dummyMovieId = 634649
    private val dummyMovieDetail = ResponseDummy.generateDummyMovieDetail()
    private val dummyEmptyMovieDetail = ResponseDummy.generateDummyEmptyMovieDetail()
    private val dummyNullMovieDetail = ResponseDummy.generateDummyNullMovieDetail()
    private val dummyErrorMovieDetail = MutableLiveData(SingleEvent(true))

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun discoverMovies() {
        every { movieRepository.discoverMovies() } answers { }
        every { movieRepository.listMovies } returns ResponseDummy.generateDummyDiscoverMovies()

        movieRepository.discoverMovies()
        val listMovies = movieRepository.listMovies.value

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

        movieRepository.discoverMovies()
        val listMovies = movieRepository.listMovies.value

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

        movieRepository.discoverMovies()
        val listMovies = movieRepository.listMovies.value
        val errorDiscoverMovies = movieRepository.errorDiscoverMovies.value

        assertNull(listMovies)
        assertNotNull(errorDiscoverMovies)
        assertTrue(errorDiscoverMovies?.peekContent() == true)

        verify {
            movieRepository.discoverMovies()
            movieRepository.listMovies
            movieRepository.errorDiscoverMovies
        }
    }

    @Test
    fun getMovieDetail() {
        every { movieRepository.getMovieDetail(dummyMovieId) } answers { }
        every { movieRepository.movieDetail } returns dummyMovieDetail

        movieRepository.getMovieDetail(dummyMovieId)
        val movieDetail = movieRepository.movieDetail.value

        assertNotNull(movieDetail)
        assertEquals(dummyMovieDetail.value, movieDetail)

        movieDetail?.apply {
            assertTrue(overview.isNotEmpty())
            assertTrue(originalLanguage.isNotEmpty())
            assertTrue(releaseDate.isNotEmpty())
            assertTrue(voteAverage != 0.0)
            assertTrue(runtime != 0)
            assertTrue(id != 0)
            assertTrue(title.isNotEmpty())
            assertTrue(tagline.isNotEmpty())
            assertTrue(posterPath.isNotEmpty())
            assertTrue(status.isNotEmpty())
        }

        verifyAll {
            movieRepository.getMovieDetail(dummyMovieId)
            movieRepository.movieDetail
        }
    }
}