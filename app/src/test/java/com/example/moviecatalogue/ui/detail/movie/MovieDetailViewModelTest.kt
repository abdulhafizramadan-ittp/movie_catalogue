package com.example.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.MovieDetail
import com.example.moviecatalogue.helper.ResponseDummy
import com.example.moviecatalogue.helper.viewModel.SingleEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private val dummyMovieId = 634649
    private val dummyMovieDetail = ResponseDummy.generateDummyMovieDetail()
    private val dummyEmptyMovieDetail = ResponseDummy.generateDummyEmptyMovieDetail()
    private val dummyNullMovieDetail = ResponseDummy.generateDummyNullMovieDetail()

    @Mock
    private lateinit var movieDetailObserver: Observer<MovieDetail>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieDetailViewModel = MovieDetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        `when`(movieRepository.getMovieDetail(dummyMovieId)).thenReturn(dummyMovieDetail)

        val movieDetail = movieDetailViewModel.getMovieDetail(dummyMovieId).value

        verify(movieRepository).getMovieDetail(dummyMovieId)

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

        movieDetailViewModel.getMovieDetail(dummyMovieId).observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyMovieDetail.value)
    }

    @Test
    fun emptyMovieDetail() {
        `when`(movieRepository.getMovieDetail(dummyMovieId)).thenReturn(dummyEmptyMovieDetail)

        val emptyMovieDetail = movieDetailViewModel.getMovieDetail(dummyMovieId).value

        verify(movieRepository).getMovieDetail(dummyMovieId)

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

        movieDetailViewModel.getMovieDetail(dummyMovieId).observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyEmptyMovieDetail.value)
    }

    @Test
    fun errorMovieDetail() {
        `when`(movieRepository.getMovieDetail(dummyMovieId)).thenReturn(dummyNullMovieDetail)

        val emptyMovieDetail = movieDetailViewModel.getMovieDetail(dummyMovieId).value

        verify(movieRepository).getMovieDetail(dummyMovieId)

        assertNull(emptyMovieDetail)
        assertEquals(dummyNullMovieDetail.value, emptyMovieDetail)
    }
}