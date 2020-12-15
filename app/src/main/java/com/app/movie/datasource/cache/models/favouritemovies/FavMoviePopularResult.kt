package com.app.movie.datasource.cache.models.favouritemovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import java.io.Serializable

@Entity(tableName = "FavMoviePopularResult")
data class FavMoviePopularResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavMoviePopularResult.asDomainModel() = MoviePopularResult(id = id)
fun List<FavMoviePopularResult>.asDomainModelList(): List<MoviePopularResult> {
    return map {
        MoviePopularResult(id = it.id)
    }
}