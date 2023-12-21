package com.dogactnrvrdi.movietimecompose.data.di

import android.content.Context
import androidx.room.Room
import com.dogactnrvrdi.movietimecompose.common.Constants
import com.dogactnrvrdi.movietimecompose.common.Constants.BASE_URL
import com.dogactnrvrdi.movietimecompose.data.repo.MovieRepositoryImpl
import com.dogactnrvrdi.movietimecompose.data.source.local.MovieDao
import com.dogactnrvrdi.movietimecompose.data.source.local.MovieDatabase
import com.dogactnrvrdi.movietimecompose.data.source.remote.MovieApi
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi,
        dao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(api, dao)

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        Constants.MOVIE_TABLE
    ).allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.getMovieDao()
}