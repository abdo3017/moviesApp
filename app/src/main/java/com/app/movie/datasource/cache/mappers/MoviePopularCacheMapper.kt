package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.MoviePopularCacheEntity
import com.app.movie.domain.models.MoviePopular
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MoviePopularCacheMapper
@Inject
constructor() : Mapper<MoviePopularCacheEntity, MoviePopular> {
    override fun mapFromEntity(entity: MoviePopularCacheEntity): MoviePopular {

        return MoviePopular(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MoviePopular): MoviePopularCacheEntity {
        return MoviePopularCacheEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MoviePopular>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MoviePopularCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
