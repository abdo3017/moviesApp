//package com.app.movie.datasource.paging
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.asLiveData
//import androidx.paging.PagingSource
//import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
//import com.app.movie.domain.models.MovieNowPlaying
//import com.app.movie.domain.repositoryimpl.MovieRepositoryImpl
//import com.app.movie.domain.state.DataState
//import com.app.movie.utils.Constants.GITHUB_STARTING_PAGE_INDEX
//import javax.inject.Inject
//
//class MoviePlayingNowPagingSourcee
//@Inject
//constructor(
//    private val movieRepository: MovieRepositoryImpl,
//) : PagingSource<Int, MovieNowPlayingResultsItem>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieNowPlayingResultsItem> {
//        val page = params.key ?: GITHUB_STARTING_PAGE_INDEX
//        val movieData:MutableLiveData<DataState<MovieNowPlaying>> = MutableLiveData((movieRepository.getMoviesNowPlaying(page) as DataState<MovieNowPlaying>))
//        when (movieData.value) {
//            is DataState.Success<MovieNowPlaying> -> {
//                return LoadResult.Page(
//                    data = (movieData.value as DataState.Success<MovieNowPlaying>).data.results,
//                    prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1,
//                    nextKey = if ((movieData.value as DataState.Success<MovieNowPlaying>).data.results.isEmpty()) null else page + 1
//                )
//            }
//        }
//        return LoadResult.Error((movieData.value as DataState.Error<*>).exception)
//
//    }
//
//
//}