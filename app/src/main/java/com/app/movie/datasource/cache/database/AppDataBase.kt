package com.app.movie.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.movie.datasource.cache.database.dao.MovieDao
import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity
import com.app.movie.datasource.cache.models.MovieVideosCacheEntity
import com.app.movie.utils.ModuleConverter

@Database(
    entities = [MovieNowPlayingCacheEntity::class, MovieVideosCacheEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ModuleConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}