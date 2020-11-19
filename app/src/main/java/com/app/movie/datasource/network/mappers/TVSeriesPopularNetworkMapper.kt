package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.TVSeriesPopularNetworkEntity
import com.app.movie.domain.models.TVSeriesPopular
import com.app.movie.utils.Mapper
import javax.inject.Inject

class TVSeriesPopularNetworkMapper
@Inject
constructor() : Mapper<TVSeriesPopularNetworkEntity, TVSeriesPopular> {
    override fun mapFromEntity(entity: TVSeriesPopularNetworkEntity): TVSeriesPopular {

        return TVSeriesPopular(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: TVSeriesPopular): TVSeriesPopularNetworkEntity {
        return TVSeriesPopularNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults
        )
    }

    fun mapToEntityList(domainModels: List<TVSeriesPopular>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<TVSeriesPopularNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
