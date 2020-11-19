package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.TVSeriesPopularCacheEntity
import com.app.movie.domain.models.TVSeriesPopular
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesPopularCacheMapper
@Inject
constructor() : Mapper<TVSeriesPopularCacheEntity, TVSeriesPopular> {
    override fun mapFromEntity(entity: TVSeriesPopularCacheEntity): TVSeriesPopular {

        return TVSeriesPopular(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesPopular): TVSeriesPopularCacheEntity {
        return TVSeriesPopularCacheEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesPopular>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesPopularCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
