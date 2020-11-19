package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.MovieUpComingNetworkEntity
import com.app.movie.domain.models.MovieUpComing
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieUpComingNetworkMapper
@Inject
constructor() : Mapper<MovieUpComingNetworkEntity, MovieUpComing> {
    override fun mapFromEntity(entity: MovieUpComingNetworkEntity): MovieUpComing {

        return MovieUpComing(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            dates = entity.dates,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieUpComing): MovieUpComingNetworkEntity {
        return MovieUpComingNetworkEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            dates = domainModel.dates,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieUpComing>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieUpComingNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
