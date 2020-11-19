package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.TVSeriesAiringTodayCacheEntity
import com.app.movie.domain.models.TVSeriesAiringToday
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesAiringTodayCacheMapper
@Inject
constructor() : Mapper<TVSeriesAiringTodayCacheEntity, TVSeriesAiringToday> {
    override fun mapFromEntity(entity: TVSeriesAiringTodayCacheEntity): TVSeriesAiringToday {

        return TVSeriesAiringToday(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesAiringToday): TVSeriesAiringTodayCacheEntity {
        return TVSeriesAiringTodayCacheEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesAiringToday>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesAiringTodayCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
