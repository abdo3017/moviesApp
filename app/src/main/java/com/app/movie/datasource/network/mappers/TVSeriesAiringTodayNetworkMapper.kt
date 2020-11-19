package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.TVSeriesAiringTodayNetworkEntity
import com.app.movie.domain.models.TVSeriesAiringToday
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesAiringTodayNetworkMapper
@Inject
constructor() : Mapper<TVSeriesAiringTodayNetworkEntity, TVSeriesAiringToday> {
    override fun mapFromEntity(entity: TVSeriesAiringTodayNetworkEntity): TVSeriesAiringToday {

        return TVSeriesAiringToday(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesAiringToday): TVSeriesAiringTodayNetworkEntity {
        return TVSeriesAiringTodayNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesAiringToday>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesAiringTodayNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
