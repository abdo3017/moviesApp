package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.movies.MovieTopRatedCacheEntity
import com.app.movie.domain.models.movies.MovieTopRated
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieTopRatedCacheMapper
@Inject
constructor() : Mapper<MovieTopRatedCacheEntity, MovieTopRated> {
    override fun mapFromEntity(entity: MovieTopRatedCacheEntity): MovieTopRated {

        return MovieTopRated(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieTopRated): MovieTopRatedCacheEntity {
        return MovieTopRatedCacheEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieTopRated>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieTopRatedCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
