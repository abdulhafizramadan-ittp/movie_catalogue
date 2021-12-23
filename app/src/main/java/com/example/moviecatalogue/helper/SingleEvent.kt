package com.example.moviecatalogue.helper

class SingleEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) {
            null
        }else {
            hasBeenHandled = true
            content
        }

    fun peekContent(): T =
        content
}