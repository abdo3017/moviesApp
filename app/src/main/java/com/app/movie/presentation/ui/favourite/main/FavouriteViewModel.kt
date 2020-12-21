package com.app.movie.presentation.ui.favourite.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.domain.repository.MovieRepository
import com.app.movie.domain.repository.TVSeriesRepository
import com.app.movie.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class FavouriteViewModel
@ViewModelInject
constructor(
    private val movieRepository: MovieRepository,
    private val tvSeriesRepository: TVSeriesRepository,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataStateMovieNowPlaying: MutableLiveData<List<MovieNowPlayingResultsItem>> =
        MutableLiveData()
    val dataStateMovieNowPlaying: LiveData<List<MovieNowPlayingResultsItem>>
        get() = _dataStateMovieNowPlaying
    private val _dataStateMovieTopRated: MutableLiveData<List<MovieTopRatedResult>> =
        MutableLiveData()
    val dataStateMovieTopRated: LiveData<List<MovieTopRatedResult>>
        get() = _dataStateMovieTopRated
    private val _dataStateMoviePopular: MutableLiveData<List<MoviePopularResult>> =
        MutableLiveData()
    val dataStateMoviePopular: LiveData<List<MoviePopularResult>>
        get() = _dataStateMoviePopular
    private val _dataStateMovieUpComing: MutableLiveData<List<MovieUpComingResult>> =
        MutableLiveData()
    val dataStateMovieUpComing: LiveData<List<MovieUpComingResult>>
        get() = _dataStateMovieUpComing

    fun getMoviesNowPlaying(): Flow<PagingData<MovieNowPlayingResultsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.moviePlayingNowPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesUpComing(): Flow<PagingData<MovieUpComingResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.movieUpComingPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesPopular(): Flow<PagingData<MoviePopularResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.moviePopularPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getMoviesTopRated(): Flow<PagingData<MovieTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieRepository.movieTopRatedPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFavMoviesNowPlaying() {
        viewModelScope.launch {
            _dataStateMovieNowPlaying.value = movieRepository.getFavMoviesNowPlaying()
        }
    }

    fun insertFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesNowPlaying(movieNowPlayingResultsItem)
        }
    }

    fun deleteFavMoviesNowPlaying(movieNowPlayingResultsItem: MovieNowPlayingResultsItem) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesNowPlaying(movieNowPlayingResultsItem)
        }
    }

    fun getFavMoviesTopRated() {
        viewModelScope.launch {
            _dataStateMovieTopRated.value = movieRepository.getFavMoviesTopRated()
        }
    }

    fun insertFavMoviesTopRated(movieTopRatedResultsItem: MovieTopRatedResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesTopRated(movieTopRatedResultsItem)
        }
    }

    fun deleteFavMoviesTopRated(movieTopRatedResultsItem: MovieTopRatedResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesTopRated(movieTopRatedResultsItem)
        }
    }

    fun getFavMoviesPopular() {
        viewModelScope.launch {
            _dataStateMoviePopular.value = movieRepository.getFavMoviesPopular()
        }
    }

    fun insertFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesPopular(moviePopularResultsItem)
        }
    }

    fun deleteFavMoviesPopular(moviePopularResultsItem: MoviePopularResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesPopular(moviePopularResultsItem)
        }
    }

    fun getFavMoviesUpComing() {
        viewModelScope.launch {
            _dataStateMovieUpComing.value = movieRepository.getFavMoviesUpComing()
        }
    }

    fun insertFavMoviesUpComing(movieUpComingResultsItem: MovieUpComingResult) {
        viewModelScope.launch {
            movieRepository.insertFavMoviesUpComing(movieUpComingResultsItem)
        }
    }

    fun deleteFavMoviesUpComing(movieUpComingResultsItem: MovieUpComingResult) {
        viewModelScope.launch {
            movieRepository.deleteFavMoviesUpComing(movieUpComingResultsItem)
        }
    }


    //////////////

    private val _dataStateTVSeriesOnTheAir: MutableLiveData<List<TVSeriesOnTheAirResult>> =
        MutableLiveData()
    val dataStateTVSeriesOnTheAir: LiveData<List<TVSeriesOnTheAirResult>>
        get() = _dataStateTVSeriesOnTheAir
    private val _dataStateTVSeriesTopRated: MutableLiveData<List<TVSeriesTopRatedResult>> =
        MutableLiveData()
    val dataStateTVSeriesTopRated: LiveData<List<TVSeriesTopRatedResult>>
        get() = _dataStateTVSeriesTopRated
    private val _dataStateTVSeriesPopular: MutableLiveData<List<TVSeriesPopularResult>> =
        MutableLiveData()
    val dataStateTVSeriesPopular: LiveData<List<TVSeriesPopularResult>>
        get() = _dataStateTVSeriesPopular
    private val _dataStateTVSeriesAiringToday: MutableLiveData<List<TVSeriesAiringTodayResult>> =
        MutableLiveData()
    val dataStateTVSeriesAiringToday: LiveData<List<TVSeriesAiringTodayResult>>
        get() = _dataStateTVSeriesAiringToday

    fun getTVSeriesOnTheAir(): Flow<PagingData<TVSeriesOnTheAirResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesOnTheAirPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesAiringToday(): Flow<PagingData<TVSeriesAiringTodayResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesAiringTodayPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesPopular(): Flow<PagingData<TVSeriesPopularResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesPopularPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getTVSeriesTopRated(): Flow<PagingData<TVSeriesTopRatedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { tvSeriesRepository.tvSeriesTopRatedPagingSource }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFavTVSeriesTopRated() {
        viewModelScope.launch {
            _dataStateTVSeriesTopRated.value = tvSeriesRepository.getFavTVTopRated()
        }
    }

    fun insertFavTVSeriesTopRated(tvTopRatedResultsItem: TVSeriesTopRatedResult) {
        viewModelScope.launch {
            tvSeriesRepository.insertFavTVTopRated(tvTopRatedResultsItem)
        }
    }

    fun deleteFavTVSeriesTopRated(tvTopRatedResultsItem: TVSeriesTopRatedResult) {
        viewModelScope.launch {
            tvSeriesRepository.deleteFavTVTopRated(tvTopRatedResultsItem)
        }
    }

    fun getFavTVSeriesPopular() {
        viewModelScope.launch {
            _dataStateTVSeriesPopular.value = tvSeriesRepository.getFavTVSeriesPopular()
        }
    }

    fun insertFavTVSeriesPopular(tvPopularResultsItem: TVSeriesPopularResult) {
        viewModelScope.launch {
            tvSeriesRepository.insertFavTVSeriesPopular(tvPopularResultsItem)
        }
    }

    fun deleteFavTVSeriesPopular(tvPopularResultsItem: TVSeriesPopularResult) {
        viewModelScope.launch {
            tvSeriesRepository.deleteFavTVSeriesPopular(tvPopularResultsItem)
        }
    }

    fun getFavTVSeriesAiringToday() {
        viewModelScope.launch {
            _dataStateTVSeriesAiringToday.value = tvSeriesRepository.getFavTVSeriesAiringToday()
        }
    }

    fun insertFavTVSeriesAiringToday(tvAiringTodayResultsItem: TVSeriesAiringTodayResult) {
        viewModelScope.launch {
            tvSeriesRepository.insertFavTVSeriesAiringToday(tvAiringTodayResultsItem)
        }
    }

    fun deleteFavTVSeriesAiringToday(tvAiringTodayResultsItem: TVSeriesAiringTodayResult) {
        viewModelScope.launch {
            tvSeriesRepository.deleteFavTVSeriesAiringToday(tvAiringTodayResultsItem)
        }
    }

    fun getFavTVSeriesOnTheAir() {
        viewModelScope.launch {
            _dataStateTVSeriesOnTheAir.value = tvSeriesRepository.getFavTVSeriesOnTheAir()
        }
    }

    fun insertFavTVSeriesOnTheAir(tvOnTheAirResultsItem: TVSeriesOnTheAirResult) {
        viewModelScope.launch {
            tvSeriesRepository.insertFavTVSeriesOnTheAir(tvOnTheAirResultsItem)
        }
    }

    fun deleteFavTVSeriesOnTheAir(tvOnTheAirResultsItem: TVSeriesOnTheAirResult) {
        viewModelScope.launch {
            tvSeriesRepository.deleteFavTVSeriesOnTheAir(tvOnTheAirResultsItem)
        }
    }

}