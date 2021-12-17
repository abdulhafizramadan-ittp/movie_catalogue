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
    fun getMovieDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyTvShowDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val movieDetail = tvShowDetailViewModel.tvShowDetail.value
        val movieDetailDummy = dummyTvShowDetail.value

        assertNotNull(movieDetail)
        assertEquals(dummyTvShowDetail.value, movieDetail)

        assertEquals(movieDetailDummy?.firstAirDate, movieDetail?.firstAirDate)
        assertEquals(movieDetailDummy?.overview, movieDetail?.overview)
        assertEquals(movieDetailDummy?.originalLanguage, movieDetail?.originalLanguage)
        assertEquals(movieDetailDummy?.type, movieDetail?.type)
        assertEquals(movieDetailDummy?.posterPath, movieDetail?.posterPath)
        assertEquals(movieDetailDummy?.voteAverage, movieDetail?.voteAverage)
        assertEquals(movieDetailDummy?.name, movieDetail?.name)
        assertEquals(movieDetailDummy?.tagline, movieDetail?.tagline)
        assertEquals(movieDetailDummy?.id, movieDetail?.id)
        assertEquals(movieDetailDummy?.numberOfSeasons, movieDetail?.numberOfSeasons)
        assertEquals(movieDetailDummy?.lastAirDate, movieDetail?.lastAirDate)
        assertEquals(movieDetailDummy?.status, movieDetail?.status)

        verifyAll {
            tvShowDetailRepository.getTvShowDetail(dummyTvShowId)
            tvShowDetailRepository.tvShowDetail
        }
    }

    @Test
    fun emptyMovieDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyEmptyTvShowDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val emptyMovieDetail = tvShowDetailViewModel.tvShowDetail.value

        assertNotNull(emptyMovieDetail)
        assertEquals(dummyEmptyTvShowDetail.value, emptyMovieDetail)

        emptyMovieDetail?.apply {
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
    fun errorMovieDetail() {
        every { tvShowDetailRepository.getTvShowDetail(dummyTvShowId) } answers { }
        every { tvShowDetailRepository.tvShowDetail } returns dummyNullTvShowDetail
        every { tvShowDetailRepository.tvShowDetailError } returns dummyTvShowMovieDetail

        tvShowDetailViewModel.getTvShowDetail(dummyTvShowId)
        val nullMovieDetail = tvShowDetailViewModel.tvShowDetail.value
        val errorMovieDetail = tvShowDetailViewModel.tvShowDetailError.value as Boolean

        assertNull(nullMovieDetail)
        assertTrue(errorMovieDetail)

        verifyAll {
            tvShowDetailRepository.getTvShowDetail(dummyTvShowId)
            tvShowDetailRepository.tvShowDetail
            tvShowDetailRepository.tvShowDetailError
        }
    }
}