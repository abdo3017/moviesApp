package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.movies.MovieNowPlayingNetworkEntity
import com.app.movie.domain.models.movies.MovieNowPlaying
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieNowPlayingNetworkMapper
@Inject
constructor() : Mapper<MovieNowPlayingNetworkEntity, MovieNowPlaying> {
    override fun mapFromEntity(entity: MovieNowPlayingNetworkEntity): MovieNowPlaying {

        return MovieNowPlaying(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            dates = entity.dates,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieNowPlaying): MovieNowPlayingNetworkEntity {
        return MovieNowPlayingNetworkEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            dates = domainModel.dates,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieNowPlaying>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieNowPlayingNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
