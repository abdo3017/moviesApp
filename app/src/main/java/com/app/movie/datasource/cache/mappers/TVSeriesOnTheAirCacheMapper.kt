package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.tv.TVSeriesOnTheAirCacheEntity
import com.app.movie.domain.models.tv.TVSeriesOnTheAir
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesOnTheAirCacheMapper
@Inject
constructor() : Mapper<TVSeriesOnTheAirCacheEntity, TVSeriesOnTheAir> {
    override fun mapFromEntity(entity: TVSeriesOnTheAirCacheEntity): TVSeriesOnTheAir {

        return TVSeriesOnTheAir(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesOnTheAir): TVSeriesOnTheAirCacheEntity {
        return TVSeriesOnTheAirCacheEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesOnTheAir>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesOnTheAirCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
