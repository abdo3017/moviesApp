package com.app.movie.datasource.cache.models.favouritemovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import java.io.Serializable

@Entity(tableName = "FavMovieNowPlayingResultsItem")
data class FavMovieNowPlayingResultsItem(
    @PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "id")
    val id: Int? = null
) : Serializable

fun FavMovieNowPlayingResultsItem.asDomainModel() = MovieNowPlayingResultsItem(id = id)
fun List<FavMovieNowPlayingResultsItem>.asDomainModelList(): List<MovieNowPlayingResultsItem> {
    return map {
        MovieNowPlayingResultsItem(id = it.id)
    }
}
