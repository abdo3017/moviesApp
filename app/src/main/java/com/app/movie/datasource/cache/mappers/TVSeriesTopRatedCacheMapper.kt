package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.tv.TVSeriesTopRatedCacheEntity
import com.app.movie.domain.models.tv.TVSeriesTopRated
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesTopRatedCacheMapper
@Inject
constructor() : Mapper<TVSeriesTopRatedCacheEntity, TVSeriesTopRated> {
    override fun mapFromEntity(entity: TVSeriesTopRatedCacheEntity): TVSeriesTopRated {

        return TVSeriesTopRated(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesTopRated): TVSeriesTopRatedCacheEntity {
        return TVSeriesTopRatedCacheEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesTopRated>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesTopRatedCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
