package com.dogactnrvrdi.movietimecompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogactnrvrdi.movietimecompose.domain.model.Movie

@Database([Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}