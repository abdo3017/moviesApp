package com.app.movie.utils

import androidx.room.TypeConverter
import com.app.movie.datasource.network.models.MovieNowPlayingDates
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi


class ModuleConverter {

    val moshi = Moshi.Builder().build()


    @TypeConverter
    fun fromMovieNowPlayingDates(value: MovieNowPlayingDates): String = fromModule(value)!!

    @TypeConverter
    fun toMovieNowPlayingDates(value: String): MovieNowPlayingDates = toModule(value)!!

    @TypeConverter
    fun fromListOfMovieNowPlayingResultsItem(value: List<MovieNowPlayingResultsItem>): String =
        fromModule(value)!!

    @TypeConverter
    fun toListOfMovieNowPlayingResultsItem(value: String): List<MovieNowPlayingResultsItem> =
        toModule(value)!!
//
//    private inline fun <reified T> fromJson(value: String): T {
//        val jsonAdapter = moshi.adapter(T::class.java)
//        return jsonAdapter.fromJson(value)!!
//    }
//
//    private inline fun <reified T> toJson(value: T): String {
//        val jsonAdapter = moshi.adapter(T::class.java)
//        return jsonAdapter.toJson(value)
//    }
//

    inline fun <reified T>  fromModule(obj: T?): String? {
        if (obj == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<T?>() {}.type
        return gson.toJson(obj, type)
    }

    inline fun <reified T>  toModule(objString: String?): T? {
        if (objString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<T?>() {}.type
        return gson.fromJson(objString, type)
    }
}