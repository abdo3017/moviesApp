package com.app.movie.datasource.cache.models.favouritemovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import java.io.Serializable

@Entity(tableName = "FavMovieTopRatedResult")
data class FavMovieTopRatedResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavMovieTopRatedResult.asDomainModel() = MovieTopRatedResult(id = id)
fun List<FavMovieTopRatedResult>.asDomainModelList(): List<MovieTopRatedResult> {
    return map {
        MovieTopRatedResult(id = it.id)
    }
}