package com.app.movie.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.movie.utils.ModuleConverter
import com.app.movie.datasource.cache.database.dao.MovieNowPlayingDao
import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity

@Database(entities = [MovieNowPlayingCacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(ModuleConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao

    companion object {
        const val DATABASE_NAME: String = "blog_db"
    }
}