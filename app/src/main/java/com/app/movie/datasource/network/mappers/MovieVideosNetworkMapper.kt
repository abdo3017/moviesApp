package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.movies.MovieVideosNetworkEntity
import com.app.movie.domain.models.movies.MovieVideos
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieVideosNetworkMapper
@Inject
constructor() : Mapper<MovieVideosNetworkEntity, MovieVideos> {
    override fun mapFromEntity(entity: MovieVideosNetworkEntity): MovieVideos {

        return MovieVideos(
            id = entity.id,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieVideos): MovieVideosNetworkEntity {
        return MovieVideosNetworkEntity(
            id = domainModel.id,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieVideos>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieVideosNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
