package com.dogactnrvrdi.movietimecompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dogactnrvrdi.movietimecompose.common.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.MOVIE_TABLE)
data class Movie(
    val originalLanguage: String?,
    val posterPath: String?,
    val releaseDate: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val overview: String?,
    val title: String?
)