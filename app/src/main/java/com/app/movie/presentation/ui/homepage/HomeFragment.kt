package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.app.movie.R
import com.app.movie.databinding.HomeFragmentBinding
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.TVSeriesTopRated
import com.app.movie.domain.state.DataState
import com.app.movie.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        getData()
        observeData()
        setViews()
        return getMRootView()
    }


    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    private fun getData() {
        getViewModel().getMoviesNowPlaying()
        getViewModel().getTVSeriesTopRated()

    }

    private fun setViews() {
        getViewDataBinding().rvMoviesPlayingNow.adapter =
            MoviePlayingNowAdapter(mutableListOf())
        getViewDataBinding().rvTVSeriesTopRated.adapter =
            TVSeriesTopRatedAdapter(mutableListOf())
    }

    private fun observeData() {
        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieNowPlaying> -> {
                    (getViewDataBinding().rvMoviesPlayingNow.adapter as MoviePlayingNowAdapter).addItems(
                        items = it.data.results as List<MovieNowPlayingResultsItem>
                    )
                    getViewDataBinding().isLoadingMovie = false
                    getViewDataBinding().loadingMovieLayout.stopShimmer()
                    getViewDataBinding().loadingMovieLayout.hideShimmer()

                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())
                }
                is DataState.Loading -> {
                    getViewDataBinding().isLoadingMovie = true
                }
            }
        })
        getViewModel().dataStateTVSeriesTopRated.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<TVSeriesTopRated> -> {
                    (getViewDataBinding().rvTVSeriesTopRated.adapter as TVSeriesTopRatedAdapter).addItems(
                        items = it.data.results
                    )
                    getViewDataBinding().isLoadingTV = false
                    getViewDataBinding().loadingTVLayout.stopShimmer()
                    getViewDataBinding().loadingTVLayout.hideShimmer()
                }
                is DataState.Error<*> -> {
                    Log.d("moviee", it.exception.toString())

                }
                is DataState.Loading -> {
                    getViewDataBinding().isLoadingTV = true

                }
            }
        })
    }

    override val layoutId: Int
        get() = R.layout.home_fragment
    override val bindingVariableId: Int
        get() = BR.homeViewModel
    override val bindingVariableValue: Any
        get() = getViewModel()


}