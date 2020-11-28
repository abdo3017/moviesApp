package com.app.movie.datasource.network.mappers

import com.app.movie.datasource.network.models.movies.MovieTopRatedNetworkEntity
import com.app.movie.domain.models.movies.MovieTopRated
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieTopRatedNetworkMapper
@Inject
constructor() : Mapper<MovieTopRatedNetworkEntity, MovieTopRated> {
    override fun mapFromEntity(entity: MovieTopRatedNetworkEntity): MovieTopRated {

        return MovieTopRated(
            page = entity.page,
            results = entity.results,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults
        )
    }

    override fun mapToEntity(domainModel: MovieTopRated): MovieTopRatedNetworkEntity {
        return MovieTopRatedNetworkEntity(
            page = domainModel.page,
            results = domainModel.results,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,

            )
    }

    fun mapToEntityList(domainModels: List<MovieTopRated>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieTopRatedNetworkEntity>) =
        entities.map { mapFromEntity(it) }
}
