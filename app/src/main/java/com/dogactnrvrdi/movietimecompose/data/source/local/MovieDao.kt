package com.dogactnrvrdi.movietimecompose.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dogactnrvrdi.movietimecompose.common.Constants
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM ${Constants.MOVIE_TABLE}")
    fun getALlMovies(): Flow<List<Movie>>
}