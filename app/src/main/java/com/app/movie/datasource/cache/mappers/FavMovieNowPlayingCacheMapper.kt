package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.favouritemovies.FavMovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.utils.Mapper
import javax.inject.Inject

class FavMovieNowPlayingCacheMapper
@Inject
constructor() : Mapper<FavMovieNowPlayingResultsItem, MovieNowPlayingResultsItem> {
    override fun mapFromEntity(entity: FavMovieNowPlayingResultsItem): MovieNowPlayingResultsItem {

        return MovieNowPlayingResultsItem(
            id = entity.id
        )
    }

    override fun mapToEntity(domainModel: MovieNowPlayingResultsItem): FavMovieNowPlayingResultsItem {
        return FavMovieNowPlayingResultsItem(
            id = domainModel.id
        )
    }

    fun mapToEntityList(domainModels: List<MovieNowPlayingResultsItem>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<FavMovieNowPlayingResultsItem>) =
        entities.map { mapFromEntity(it) }
}
