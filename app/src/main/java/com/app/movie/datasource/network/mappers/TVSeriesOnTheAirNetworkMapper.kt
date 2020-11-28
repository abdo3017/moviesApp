package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirNetworkEntity
import com.app.movie.domain.models.tv.TVSeriesOnTheAir
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesOnTheAirNetworkMapper
@Inject
constructor() : Mapper<TVSeriesOnTheAirNetworkEntity, TVSeriesOnTheAir> {
    override fun mapFromEntity(entity: TVSeriesOnTheAirNetworkEntity): TVSeriesOnTheAir {

        return TVSeriesOnTheAir(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesOnTheAir): TVSeriesOnTheAirNetworkEntity {
        return TVSeriesOnTheAirNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesOnTheAir>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesOnTheAirNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
