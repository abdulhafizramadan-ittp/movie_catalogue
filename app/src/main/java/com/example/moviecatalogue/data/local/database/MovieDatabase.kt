package com.example.moviecatalogue.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.local.entity.MovieEntity
import com.example.moviecatalogue.data.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}