package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.example.moviecatalogue.data.local.LocalDataSource
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var movieRepository: MovieRepository

    private val dummyDiscoverMovies = ResponseDummy.dummyDiscoverMoviesResponse()
    private val dummyDiscoverTvShows = ResponseDummy.dummyDiscoverTvShowsResponse()
    private val dummyMovieDetail = ResponseDummy.dummyMovieDetailResponse()
    private val dummyMovieId = dummyMovieDetail.id as Int
    private val dummyTvShowDetail = ResponseDummy.dummyTvShowDetailResponses()
    private val dummyTvShowId = dummyTvShowDetail.id as Int

    private val dummyTvShowEntity = TvShowEntity(0, "", "")
    private val dummyMovieEntity = MovieEntity(0, "", "")

    @Before
    fun setUp() {
        movieRepository = MovieRepository(remoteDataSource, localDataSource)
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

        val movieDetail = LiveDataTestUtil
            .getValue(movieRepository.getTvShowDetail(dummyTvShowId))

        verify(remoteDataSource).getTvShowDetail(any(), eq(dummyTvShowId))
        assertNotNull(movieDetail)
    }

    @Test
    fun getAllFavoriteMovies() {
        val dataSource: DataSource.Factory<Int, MovieEntity> = mock() as DataSource.Factory<Int, MovieEntity>
        val dummyListMovies = LivePagedListBuilder(dataSource, 20).build()

        `when`(localDataSource.getAllFavoriteMovies())
            .thenReturn(dummyListMovies)

        val listMovies = movieRepository.getAllFavoriteMovies()

        verify(localDataSource)
            .getAllFavoriteMovies()

        assertNotNull(listMovies)
    }

    @Test
    fun getAllFavoriteTvShows() {
        val dataSource: DataSource.Factory<Int, TvShowEntity> = mock() as DataSource.Factory<Int, TvShowEntity>
        val dummyListTvShows = LivePagedListBuilder(dataSource, 20).build()

        `when`(localDataSource.getAllFavoriteTvShows())
            .thenReturn(dummyListTvShows)

        val listTvShows = movieRepository.getAllFavoriteTvShows()

        verify(localDataSource)
            .getAllFavoriteTvShows()

        assertNotNull(listTvShows)
    }

    @Test
    fun insertFavoriteMovie() {
        doNothing()
            .`when`(localDataSource)
            .insertFavoriteMovie(dummyMovieEntity)

        movieRepository.insertFavoriteMovie(dummyMovieEntity)

        verify(localDataSource).insertFavoriteMovie(dummyMovieEntity)
    }

    @Test
    fun insertFavoriteTvShow() {
        doNothing()
            .`when`(localDataSource)
            .insertFavoriteTvShow(dummyTvShowEntity)

        movieRepository
            .insertFavoriteTvShow(dummyTvShowEntity)

        verify(localDataSource)
            .insertFavoriteTvShow(dummyTvShowEntity)
    }

    @Test
    fun deleteFavoriteMovie() {
        doNothing()
            .`when`(localDataSource)
            .deleteFavoriteMovie(dummyMovieEntity)

        movieRepository
            .deleteFavoriteMovie(dummyMovieEntity)

        verify(localDataSource)
            .deleteFavoriteMovie(dummyMovieEntity)
    }

    @Test
    fun deleteFavoriteTvShow() {
        doNothing()
            .`when`(localDataSource)
            .deleteFavoriteTvShow(dummyTvShowEntity)

        movieRepository
            .deleteFavoriteTvShow(dummyTvShowEntity)

        verify(localDataSource)
            .deleteFavoriteTvShow(dummyTvShowEntity)
    }
}