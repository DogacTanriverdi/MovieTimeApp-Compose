package com.dogactnrvrdi.movietimecompose.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dogactnrvrdi.movietimecompose.common.Constants.BASE_URL
import com.dogactnrvrdi.movietimecompose.data.repo.MovieRepositoryImpl
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

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor) = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }
        )
        addInterceptor(chuckerInterceptor)
        readTimeout(60, TimeUnit.SECONDS)
        connectTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
    }.build()


    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)
}