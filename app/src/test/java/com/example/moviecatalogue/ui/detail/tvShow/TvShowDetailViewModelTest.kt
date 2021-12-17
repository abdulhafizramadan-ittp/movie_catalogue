package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.TvShowDetailRepository
import com.example.moviecatalogue.helper.ResponseDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowDetailViewModelTest {

    private lateinit var tvShowDetailRepository: TvShowDetailRepository
    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel

    private val dummyTvShowId = 71914
    private val dummyTvShowDetail = ResponseDummy.generateDummyTvShowDetail()
    private val dummyEmptyTvShowDetail = ResponseDummy.generateDummyEmptyTvShowDetail()
    private val dummyNullTvShowDetail = ResponseDummy.generateDummyNullTvShowDetail()
    private val dummyTvShowMovieDetail = MutableLiveData(true)

    @Before
    fun setUp() {
        tvShowDetailRepository = mockk()
        tvShowDetailViewModel = TvShowDetailViewModel(tvShowDetailRepository)
    }

    @Test
    fun getTvShowDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyTvShowDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val tvShowDetail = tvShowDetailViewModel.tvShowDetail.value

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

        verifyAll {
            tvShowDetailRepository.getTvShowDetail(dummyTvShowId)
            tvShowDetailRepository.tvShowDetail
        }
    }

    @Test
    fun emptyTvShowDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyEmptyTvShowDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val emptyTvShowDetail = tvShowDetailViewModel.tvShowDetail.value

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

        verifyAll {
            tvShowDetailRepository.getTvShowDetail(dummyTvShowId)
            tvShowDetailRepository.tvShowDetail
        }
    }

    @Test
    fun errorTvShowDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyNullTvShowDetail
        every { tvShowDetailRepository.tvShowDetailError } returns dummyTvShowMovieDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val nullTvShowDetail = tvShowDetailViewModel.tvShowDetail.value
        val errorTvShowDetail = tvShowDetailViewModel.tvShowDetailError.value as Boolean

        assertNull(nullTvShowDetail)
        assertNotNull(errorTvShowDetail)
        assertTrue(errorTvShowDetail)

        verifyAll {
            tvShowDetailRepository.getTvShowDetail(dummyTvShowId)
            tvShowDetailRepository.tvShowDetail
            tvShowDetailRepository.tvShowDetailError
        }
    }
}