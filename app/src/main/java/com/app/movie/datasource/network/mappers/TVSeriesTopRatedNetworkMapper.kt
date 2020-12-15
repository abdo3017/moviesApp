package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedNetworkEntity
import com.app.movie.domain.models.tv.TVSeriesTopRated
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesTopRatedNetworkMapper
@Inject
constructor() : Mapper<TVSeriesTopRatedNetworkEntity, TVSeriesTopRated> {
    override fun mapFromEntity(entity: TVSeriesTopRatedNetworkEntity): TVSeriesTopRated {

        return TVSeriesTopRated(
            page = entity.page!!,
            results = entity.results!!,
            totalPages = entity.totalPages!!,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesTopRated): TVSeriesTopRatedNetworkEntity {
        return TVSeriesTopRatedNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesTopRated>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesTopRatedNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
