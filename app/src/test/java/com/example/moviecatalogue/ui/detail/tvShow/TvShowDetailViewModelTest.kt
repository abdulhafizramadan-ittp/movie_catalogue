package com.example.moviecatalogue.ui.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.domain.TvShowDetail
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
class TvShowDetailViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel

    private val dummyTvShowId = 71914
    private val dummyTvShowDetail = ResponseDummy.generateDummyTvShowDetail()
    private val dummyEmptyTvShowDetail = ResponseDummy.generateDummyEmptyTvShowDetail()
    private val dummyNullTvShowDetail = ResponseDummy.generateDummyNullTvShowDetail()

    @Mock
    private lateinit var tvShowDetailObserver: Observer<TvShowDetail>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tvShowDetailViewModel = TvShowDetailViewModel(movieRepository)
    }

    @Test
    fun getTvShowDetail() {
        `when`(movieRepository.getTvShowDetail(dummyTvShowId)).thenReturn(dummyTvShowDetail)

        val tvShowDetail = tvShowDetailViewModel.getTvShowDetail(dummyTvShowId).value

        verify(movieRepository).getTvShowDetail(dummyTvShowId)

        assertNotNull(tvShowDetail)
        assertEquals(dummyTvShowDetail.value, tvShowDetail)

        tvShowDetail?.apply {
            assertTrue(firstAirDate.isNotEmpty())
            assertTrue(overview.isNotEmpty())
            assertTrue(originalLanguage.isNotEmpty())
            assertTrue(type.isNotEmpty())
            assertTrue(posterPath.isNotEmpty())
            assertTrue(voteAverage != 0.0)
            assertTrue(name.isNotEmpty())
            assertTrue(tagline.isNotEmpty())
            assertTrue(id != 0)
            assertTrue(numberOfSeasons != 0)
            assertTrue(lastAirDate.isNotEmpty())
            assertTrue(status.isNotEmpty())
        }

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId).observeForever(tvShowDetailObserver)
        verify(tvShowDetailObserver).onChanged(dummyTvShowDetail.value)
    }

    @Test
    fun emptyTvShowDetail() {
        `when`(movieRepository.getTvShowDetail(dummyTvShowId)).thenReturn(dummyEmptyTvShowDetail)

        val emptyTvShowDetail = tvShowDetailViewModel.getTvShowDetail(dummyTvShowId).value

        verify(movieRepository).getTvShowDetail(dummyTvShowId)

        assertNotNull(emptyTvShowDetail)
        assertEquals(dummyEmptyTvShowDetail.value, emptyTvShowDetail)

        emptyTvShowDetail?.apply {
            assertTrue(firstAirDate.isEmpty())
            assertTrue(overview.isEmpty())
            assertTrue(originalLanguage.isEmpty())
            assertTrue(type.isEmpty())
            assertTrue(posterPath.isEmpty())
            assertTrue(voteAverage == 0.0)
            assertTrue(name.isEmpty())
            assertTrue(tagline.isEmpty())
            assertTrue(id == 0)
            assertTrue(status.isEmpty())
            assertTrue(lastAirDate.isEmpty())
            assertTrue(status.isEmpty())
        }

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId).observeForever(tvShowDetailObserver)
        verify(tvShowDetailObserver).onChanged(dummyEmptyTvShowDetail.value)
    }

    @Test
    fun errorTvShowDetail() {
        `when`(movieRepository.getTvShowDetail(dummyTvShowId)).thenReturn(dummyNullTvShowDetail)

        val nullTvShowDetail = tvShowDetailViewModel.getTvShowDetail(dummyTvShowId).value

        verify(movieRepository).getTvShowDetail(dummyTvShowId)

        assertNull(nullTvShowDetail)
        assertEquals(dummyNullTvShowDetail.value, nullTvShowDetail)
    }
}