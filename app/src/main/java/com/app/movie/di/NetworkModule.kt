package com.app.movie.di

import com.app.movie.datasource.network.MovieService
import com.app.movie.datasource.network.TVService
import com.app.movie.datasource.network.mappers.MovieNowPlayingNetworkMapper
import com.app.movie.datasource.network.mappers.TVSeriesTopRatedNetworkMapper
import com.app.movie.datasource.network.models.MovieNowPlayingNetworkEntity
import com.app.movie.datasource.network.models.TVSeriesTopRatedNetworkEntity
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.TVSeriesTopRated
import com.app.movie.utils.Constants
import com.app.movie.utils.Mapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule  {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieNowPlayingNetworkMapper(): Mapper<MovieNowPlayingNetworkEntity, MovieNowPlaying> {
        return MovieNowPlayingNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesTopRatedNetworkMapper(): Mapper<TVSeriesTopRatedNetworkEntity, TVSeriesTopRated> {
        return TVSeriesTopRatedNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit
            .create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideTVService(retrofit: Retrofit): TVService {
        return retrofit
            .create(TVService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}