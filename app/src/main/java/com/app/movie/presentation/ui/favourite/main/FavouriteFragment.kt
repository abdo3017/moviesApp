package com.app.movie.presentation.ui.favourite.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.app.movie.R
import com.app.movie.databinding.FragmentFavouriteBinding
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.ui.favourite.movie.FavouriteMovieFragment
import com.app.movie.presentation.ui.favourite.tv.FavouriteTVFragment
import com.app.movie.utils.ViewPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>() {

    private val favouriteViewModel: FavouriteViewModel by viewModels()
    private lateinit var itemsMovieNowPlaying: Flow<PagingData<MovieNowPlayingResultsItem>>
    private lateinit var itemsMovieTopRated: Flow<PagingData<MovieTopRatedResult>>
    private lateinit var itemsMovieUpComing: Flow<PagingData<MovieUpComingResult>>
    private lateinit var itemsMoviePopular: Flow<PagingData<MoviePopularResult>>
    private lateinit var itemsTVOnTheAir: Flow<PagingData<TVSeriesOnTheAirResult>>
    private lateinit var itemsTVTopRated: Flow<PagingData<TVSeriesTopRatedResult>>
    private lateinit var itemsTVAiringToday: Flow<PagingData<TVSeriesAiringTodayResult>>
    private lateinit var itemsTVPopular: Flow<PagingData<TVSeriesPopularResult>>
    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        fetchData()
        setupMoviesViewPager()
        onClick()
        return getMRootView()
    }

    private fun fetchData() {
        runBlocking<Unit> {
            val one = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesNowPlaying().apply {
                    itemsMovieNowPlaying = this

                }
            }
            val two = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesTopRated().apply {
                    itemsMovieTopRated = this
                }
            }
            val three = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesUpComing().apply {
                    itemsMovieUpComing = this
                }
            }
            val four = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesPopular().apply {
                    itemsMoviePopular = this
                }
            }
            // some computation

            val one1 = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesOnTheAir().apply {
                    itemsTVOnTheAir = this
                }
            }
            val two1 = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesTopRated().apply {
                    itemsTVTopRated = this
                }
            }
            val three1 = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesAiringToday().apply {
                    itemsTVAiringToday = this
                }
            }
            val four1 = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesPopular().apply {
                    itemsTVPopular = this
                }
            }
            // some computation
            one.start() // start the first one
            two.start()
            three.start()
            four.start()
            one1.start() // start the first one
            two1.start()
            three1.start()
            four1.start()
        }
    }

    private fun setupMoviesViewPager() {
        getViewDataBinding().tpFavourite.setupWithViewPager(getViewDataBinding().vpFavourite, true)
        viewPageAdapter = ViewPageAdapter(childFragmentManager, mutableListOf())
        getViewDataBinding().vpFavourite.adapter = viewPageAdapter

        viewPageAdapter.addFragment(
            FavouriteMovieFragment(
                itemsMovieNowPlaying,
                itemsMoviePopular,
                itemsMovieTopRated,
                itemsMovieUpComing
            )
        )
        viewPageAdapter.addFragment(
            FavouriteTVFragment(
                itemsTVOnTheAir,
                itemsTVPopular,
                itemsTVTopRated,
                itemsTVAiringToday
            )
        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_favourite
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel(): FavouriteViewModel {
        return favouriteViewModel
    }

    private fun onClick() {
        getViewDataBinding().tvFavMovies.setOnClickListener {
            getViewDataBinding().vpFavourite.currentItem = 0
        }
        getViewDataBinding().tvFavTV.setOnClickListener {
            getViewDataBinding().vpFavourite.currentItem = 1
        }
    }

}