package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.MovieVideosCacheEntity
import com.app.movie.domain.models.MovieVideos
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieVideosCacheMapper
@Inject
constructor() : Mapper<MovieVideosCacheEntity, MovieVideos> {
    override fun mapFromEntity(entity: MovieVideosCacheEntity): MovieVideos {

        return MovieVideos(
            id = entity.id,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieVideos): MovieVideosCacheEntity {
        return MovieVideosCacheEntity(
            id = domainModel.id,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieVideos>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieVideosCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
