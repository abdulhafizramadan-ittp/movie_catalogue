package com.example.moviecatalogue.helper

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoIdlingTestResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingTestResource.increment()

    fun decrement() = espressoIdlingTestResource.decrement()

    fun getEspressoIdlingResource() = espressoIdlingTestResource
}