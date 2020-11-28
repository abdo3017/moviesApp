package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.movies.MovieUpComingCacheEntity
import com.app.movie.domain.models.movies.MovieUpComing
import com.app.movie.utils.Mapper
import javax.inject.Inject

class MovieUpComingCacheMapper
@Inject
constructor() : Mapper<MovieUpComingCacheEntity, MovieUpComing> {
    override fun mapFromEntity(entity: MovieUpComingCacheEntity): MovieUpComing {

        return MovieUpComing(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            dates = entity.dates,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieUpComing): MovieUpComingCacheEntity {
        return MovieUpComingCacheEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            dates = domainModel.dates,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieUpComing>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieUpComingCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
