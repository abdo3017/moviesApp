package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.movies.MoviePopularNetworkEntity
import com.app.movie.domain.models.movies.MoviePopular
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MoviePopularNetworkMapper
@Inject
constructor() : Mapper<MoviePopularNetworkEntity, MoviePopular> {
    override fun mapFromEntity(entity: MoviePopularNetworkEntity): MoviePopular {

        return MoviePopular(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: MoviePopular): MoviePopularNetworkEntity {
        return MoviePopularNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,

            )
    }

    fun mapToEntityList(domainModels: List<MoviePopular>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MoviePopularNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
