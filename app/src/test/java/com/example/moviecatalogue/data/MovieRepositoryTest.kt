package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.remote.RemoteDataSource
import com.example.moviecatalogue.helper.ResponseDummy
import com.example.moviecatalogue.helper.unitTest.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var movieRepository: MovieRepository

    private val dummyDiscoverMovies = ResponseDummy.dummyDiscoverMoviesResponse()
    private val dummyDiscoverTvShows = ResponseDummy.dummyDiscoverTvShowsResponse()
    private val dummyMovieDetail = ResponseDummy.dummyMovieDetailResponse()
    private val dummyMovieId = dummyMovieDetail.id as Int
    private val dummyTvShowDetail = ResponseDummy.dummyTvShowDetailResponses()
    private val dummyTvShowId = dummyTvShowDetail.id as Int

    @Before
    fun setUp() {
        movieRepository = MovieRepository(remoteDataSource)
    }

    @Test
    fun discoverMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(dummyDiscoverMovies)
            null
        }.`when`(remoteDataSource).discoverMovies(any())

        val listMovies = LiveDataTestUtil.getValue(movieRepository.discoverMovies())
        verify(remoteDataSource).discoverMovies(any())
        assertNotNull(listMovies)
        assertEquals(dummyDiscoverMovies.size, listMovies.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(dummyMovieDetail)
            null
        }.`when`(remoteDataSource).getMovieDetail(any(), eq(dummyMovieId))

        val movieDetail = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(dummyMovieId))
        verify(remoteDataSource).getMovieDetail(any(), eq(dummyMovieId))
        assertNotNull(movieDetail)
    }

    @Test
    fun discoverTvShows() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(dummyDiscoverTvShows)
            null
        }.`when`(remoteDataSource).discoverTvShows(any())

        val listTvShows = LiveDataTestUtil.getValue(movieRepository.discoverTvShows())
        verify(remoteDataSource).discoverTvShows(any())
        assertNotNull(listTvShows)
        assertEquals(dummyDiscoverTvShows.size, listTvShows.size)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvShowDetailCallback)
                .onTvShowDetailReceived(dummyTvShowDetail)
            null
        }.`when`(remoteDataSource).getTvShowDetail(any(), eq(dummyTvShowId))

        val movieDetail = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(dummyTvShowId))
        verify(remoteDataSource).getTvShowDetail(any(), eq(dummyTvShowId))
        assertNotNull(movieDetail)
    }
}