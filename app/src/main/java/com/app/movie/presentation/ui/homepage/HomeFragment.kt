package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.movie.R
import com.app.movie.databinding.HomeFragmentBinding
import com.app.movie.datasource.network.models.MovieVideosResultsItem
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.MovieVideos
import com.app.movie.domain.state.DataState
import com.app.movie.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val moviesTrailersList: MutableList<MovieVideosResultsItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        getTrailers()
        observeData()
        setViews()
        return getMRootView()
    }


    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    private fun getTrailers() {
        getViewModel().getMoviesNowPlaying()

    }

    private fun setViews() {
        getViewDataBinding().recommendedVideosTrailerRv.adapter =
            MoviesTrailerAdapter(moviesTrailersList)
    }

    private fun observeData() {
        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<MovieNowPlaying> -> {
                    for (i in it.data.results!!) {
                        Log.d("movie", i!!.id.toString())
                        getViewModel().getMovieVideos(i.id!!)
                    }
                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())

                }
                is DataState.Loading -> {

                }
            }
        })
        getViewModel().dataStateMovieVideos.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success<MovieVideos> -> {
                    Log.d("moviee", it.data.results!![0]!!.key.toString())
                    moviesTrailersList.add(it.data.results[0]!!)
                }
                is DataState.Error<*> -> {
                    Log.d("moviee", it.exception.toString())

                }
                is DataState.Loading -> {

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