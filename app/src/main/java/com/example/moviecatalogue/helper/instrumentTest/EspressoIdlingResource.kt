package com.example.moviecatalogue.helper.instrumentTest

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoIdlingTestResource = CountingIdlingResource(RESOURCE)

    fun increment() = espressoIdlingTestResource.increment()

    fun decrement() = espressoIdlingTestResource.decrement()

    fun ifNotIdlingDecrement() {
        if (!espressoIdlingTestResource.isIdleNow) {
            decrement()
        }
    }

    fun getEspressoIdlingResource() = espressoIdlingTestResource
}