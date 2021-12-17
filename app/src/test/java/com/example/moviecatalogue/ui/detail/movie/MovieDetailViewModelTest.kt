package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.MovieDetailRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MovieDetailViewModelTest {

    private lateinit var movieDetailRepository: MovieDetailRepository
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private val dummyMovieId = 634649
    private val dummyMovieDetail = ResponseDummy.generateDummyMovieDetail()
    private val dummyEmptyMovieDetail = ResponseDummy.generateDummyEmptyMovieDetail()
    private val dummyNullMovieDetail = ResponseDummy.generateDummyNullMovieDetail()
    private val dummyErrorMovieDetail = MutableLiveData(true)

    @Before
    fun setUp() {
        movieDetailRepository = mockk()
        movieDetailViewModel = MovieDetailViewModel(movieDetailRepository)
    }

    @Test
    fun getMovieDetail() {
        every { movieDetailRepository.getMovieDetail(dummyMovieId) } answers { }
        every { movieDetailRepository.movieDetail } returns dummyMovieDetail

        movieDetailViewModel.getMovieDetail(dummyMovieId)
        val movieDetail = movieDetailViewModel.movieDetail.value

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
            movieDetailRepository.getMovieDetail(dummyMovieId)
            movieDetailRepository.movieDetail
        }
    }

    @Test
    fun emptyMovieDetail() {
        every { movieDetailRepository.getMovieDetail(dummyMovieId) } answers { }
        every { movieDetailRepository.movieDetail } returns dummyEmptyMovieDetail

        movieDetailViewModel.getMovieDetail(dummyMovieId)
        val emptyMovieDetail = movieDetailViewModel.movieDetail.value

        assertNotNull(emptyMovieDetail)
        assertEquals(dummyEmptyMovieDetail.value, emptyMovieDetail)

        emptyMovieDetail?.apply {
            assertTrue(overview.isEmpty())
            assertTrue(originalLanguage.isEmpty())
            assertTrue(releaseDate.isEmpty())
            assertTrue(voteAverage == 0.0)
            assertTrue(runtime == 0)
            assertTrue(id == 0)
            assertTrue(title.isEmpty())
            assertTrue(tagline.isEmpty())
            assertTrue(posterPath.isEmpty())
            assertTrue(status.isEmpty())
        }

        verifyAll {
            movieDetailRepository.getMovieDetail(dummyMovieId)
            movieDetailRepository.movieDetail
        }
    }

    @Test
    fun errorMovieDetail() {
        every { movieDetailRepository.getMovieDetail(dummyMovieId) } answers { }
        every { movieDetailRepository.movieDetail } returns dummyNullMovieDetail
        every { movieDetailRepository.movieDetailError } returns dummyErrorMovieDetail

        movieDetailViewModel.getMovieDetail(dummyMovieId)
        val nullMovieDetail = movieDetailViewModel.movieDetail.value
        val errorMovieDetail = movieDetailViewModel.movieDetailError.value as Boolean

        assertNull(nullMovieDetail)
        assertNotNull(errorMovieDetail)
        assertTrue(errorMovieDetail)

        verifyAll {
            movieDetailRepository.getMovieDetail(dummyMovieId)
            movieDetailRepository.movieDetail
            movieDetailRepository.movieDetailError
        }
    }
}