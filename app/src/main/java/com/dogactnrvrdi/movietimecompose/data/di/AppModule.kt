package com.dogactnrvrdi.movietimecompose.data.di

import com.dogactnrvrdi.movietimecompose.common.Constants.BASE_URL
import com.dogactnrvrdi.movietimecompose.data.repo.MovieRepositoryImpl
import com.dogactnrvrdi.movietimecompose.data.source.remote.MovieApi
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)
}