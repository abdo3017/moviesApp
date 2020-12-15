package com.app.movie.datasource.cache.models.favouritemovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import java.io.Serializable

@Entity(tableName = "FavMovieUpComingResult")

data class FavMovieUpComingResult(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int = 0
) : Serializable

fun FavMovieUpComingResult.asDomainModel() = MovieUpComingResult(id = id)
fun List<FavMovieUpComingResult>.asDomainModelList(): List<MovieUpComingResult> {
    return map {
        MovieUpComingResult(id = it.id)
    }
}
