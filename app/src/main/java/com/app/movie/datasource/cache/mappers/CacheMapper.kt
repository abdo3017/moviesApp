package com.app.movie.datasource.cache.mappers

import com.app.movie.datasource.cache.models.MovieNowPlayingCacheEntity
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.utils.Mapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): Mapper<MovieNowPlayingCacheEntity, MovieNowPlaying> {
    override fun mapFromEntity(entity: MovieNowPlayingCacheEntity): MovieNowPlaying {

        return MovieNowPlaying(
            page = entity.page,
            totalPages = entity.totalPages,
            totalResults = entity.totalResults,
            dates = entity.dates,
            results = entity.results
        )
    }

    override fun mapToEntity(domainModel: MovieNowPlaying): MovieNowPlayingCacheEntity {
        return MovieNowPlayingCacheEntity(
            page = domainModel.page,
            totalPages = domainModel.totalPages,
            totalResults = domainModel.totalResults,
            dates = domainModel.dates,
            results = domainModel.results
        )
    }

    fun mapToEntityList(domainModels: List<MovieNowPlaying>) =
        domainModels.map { mapToEntity(it) }

    fun mapFromEntityList(entities: List<MovieNowPlayingCacheEntity>) =
        entities.map { mapFromEntity(it) }
}
