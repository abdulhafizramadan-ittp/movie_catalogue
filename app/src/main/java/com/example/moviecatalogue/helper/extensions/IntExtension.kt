package com.example.moviecatalogue.helper.extensions

fun Int.runtimeToHour(): Int =
    this / 60

fun Int.runtimeToMinute(): Int =
    this % 60
