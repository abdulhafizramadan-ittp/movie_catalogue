package com.example.moviecatalogue.ui.detail.tvShow

import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.repository.TvShowRepository
import com.example.moviecatalogue.helper.ResponseDummy
import com.example.moviecatalogue.helper.viewModel.SingleEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowDetailViewModelTest {

    private lateinit var tvShowRepository: TvShowRepository
    private lateinit var tvShowDetailViewModel: FakeTvShowDetailViewModel

    private val dummyTvShowId = 71914
    private val dummyTvShowDetail = ResponseDummy.generateDummyTvShowDetail()
    private val dummyEmptyTvShowDetail = ResponseDummy.generateDummyEmptyTvShowDetail()
    private val dummyNullTvShowDetail = ResponseDummy.generateDummyNullTvShowDetail()
    private val dummyTvShowMovieDetail = MutableLiveData(SingleEvent(true))

    @Before
    fun setUp() {
        tvShowRepository = mockk()
        tvShowDetailViewModel = FakeTvShowDetailViewModel(tvShowRepository)
    }

    @Test
    fun getTvShowDetail() {
        every { tvShowRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowRepository.tvShowDetail } returns dummyTvShowDetail

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
            tvShowRepository.getTvShowDetail(dummyTvShowId)
            tvShowRepository.tvShowDetail
        }
    }

    @Test
    fun emptyTvShowDetail() {
        every { tvShowRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowRepository.tvShowDetail } returns dummyEmptyTvShowDetail

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
            tvShowRepository.getTvShowDetail(dummyTvShowId)
            tvShowRepository.tvShowDetail
        }
    }

    @Test
    fun errorTvShowDetail() {
        every { tvShowRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowRepository.tvShowDetail } returns dummyNullTvShowDetail
        every { tvShowRepository.tvShowDetailError } returns dummyTvShowMovieDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val nullTvShowDetail = tvShowDetailViewModel.tvShowDetail.value
        val errorTvShowDetail = tvShowDetailViewModel.tvShowDetailError.value

        assertNull(nullTvShowDetail)
        assertNotNull(errorTvShowDetail)
        assertTrue(errorTvShowDetail?.peekContent() == true)

        verifyAll {
            tvShowRepository.getTvShowDetail(dummyTvShowId)
            tvShowRepository.tvShowDetail
            tvShowRepository.tvShowDetailError
        }
    }
}