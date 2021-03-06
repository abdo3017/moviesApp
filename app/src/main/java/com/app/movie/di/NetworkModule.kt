package com.app.movie.di

import com.app.movie.datasource.network.api.TVSeries.TVService
import com.app.movie.datasource.network.api.movies.MovieService
import com.app.movie.datasource.network.mappers.*
import com.app.movie.datasource.network.models.movies.*
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesPopularNetworkEntity
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedNetworkEntity
import com.app.movie.domain.models.movies.*
import com.app.movie.domain.models.tv.TVSeriesAiringToday
import com.app.movie.domain.models.tv.TVSeriesOnTheAir
import com.app.movie.domain.models.tv.TVSeriesPopular
import com.app.movie.domain.models.tv.TVSeriesTopRated
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
object NetworkModule {

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
    fun provideMovieVideosNetworkMapper(): Mapper<MovieVideosNetworkEntity, MovieVideos> {
        return MovieVideosNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideMovieTopRatedNetworkMapper(): Mapper<MovieTopRatedNetworkEntity, MovieTopRated> {
        return MovieTopRatedNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideMoviePopularNetworkMapper(): Mapper<MoviePopularNetworkEntity, MoviePopular> {
        return MoviePopularNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideMovieUpComingNetworkMapper(): Mapper<MovieUpComingNetworkEntity, MovieUpComing> {
        return MovieUpComingNetworkMapper()
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
    fun provideTVSeriesPopularNetworkMapper(): Mapper<TVSeriesPopularNetworkEntity, TVSeriesPopular> {
        return TVSeriesPopularNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesOnTheAirNetworkMapper(): Mapper<TVSeriesOnTheAirNetworkEntity, TVSeriesOnTheAir> {
        return TVSeriesOnTheAirNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideTVSeriesAiringTodayNetworkMapper(): Mapper<TVSeriesAiringTodayNetworkEntity, TVSeriesAiringToday> {
        return TVSeriesAiringTodayNetworkMapper()
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