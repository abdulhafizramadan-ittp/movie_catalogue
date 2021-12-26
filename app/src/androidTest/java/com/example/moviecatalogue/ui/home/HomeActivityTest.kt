package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviecatalogue.R
import com.example.moviecatalogue.helper.instrumentTest.EspressoIdlingResource
import com.example.moviecatalogue.helper.ResponseDummy
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    private val discoverMoviesLength = ResponseDummy.generateDummyDiscoverMovies().value?.size as Int

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadDiscoverMovies() {
        onView(withId(R.id.rv_movie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(discoverMoviesLength))
        }
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_synopsis)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDiscoverTvShows() {
        onView(withText("Tv Show")).apply {
            check(matches(isDisplayed()))
            perform(click())
        }

        onView(withId(R.id.rv_tv_show)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(discoverMoviesLength))
        }
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Show")).apply {
            check(matches(isDisplayed()))
            perform(click())
        }

        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_tv_show_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_season)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_synopsis)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }
}