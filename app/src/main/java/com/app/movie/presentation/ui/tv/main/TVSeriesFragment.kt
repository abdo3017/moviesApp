package com.app.movie.presentation.ui.tv.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.app.movie.R
import com.app.movie.databinding.FragmentTVBinding
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.ui.movies.main.MoviesViewModel
import com.app.movie.presentation.ui.movies.playingnow.MoviePlayingNowFragment
import com.app.movie.presentation.ui.movies.popular.MoviePopularFragment
import com.app.movie.presentation.ui.movies.toprated.MovieTopRatedFragment
import com.app.movie.presentation.ui.movies.upcoming.MovieUpComingFragment
import com.app.movie.utils.ViewPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TVSeriesFragment : BaseFragment<FragmentTVBinding, MoviesViewModel>() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var itemsNowPlaying: Flow<PagingData<MovieNowPlayingResultsItem>>
    private lateinit var itemsTopRated: Flow<PagingData<MovieTopRatedResult>>
    private lateinit var itemsUpComing: Flow<PagingData<MovieUpComingResult>>
    private lateinit var itemsPopular: Flow<PagingData<MoviePopularResult>>
    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setupMoviesViewPager()
        fetchData()
        onClick()
        observeData()
        return getMRootView()
    }

    private fun fetchData() {
        runBlocking<Unit> {
            val one = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesNowPlaying().apply {
                    itemsNowPlaying = this
                    viewPageAdapter.addFragment(MoviePlayingNowFragment(itemsNowPlaying))

                }
            }
            val two = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesTopRated().apply {
                    itemsTopRated = this
                    viewPageAdapter.addFragment(MovieTopRatedFragment(itemsTopRated))
                }
            }
            val three = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesUpComing().apply {
                    itemsUpComing = this
                    viewPageAdapter.addFragment(MovieUpComingFragment(itemsUpComing))
                }
            }
            val four = async(start = CoroutineStart.LAZY) {
                getViewModel().getMoviesPopular().apply {
                    itemsPopular = this
                    viewPageAdapter.addFragment(MoviePopularFragment(itemsPopular))
                }
            }
            // some computation
            one.start() // start the first one
            two.start()
            three.start()
            four.start()
        }
    }

    private fun observeData() {
/*
        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieNowPlaying> -> {
                    itemsNowPlaying = MutableLiveData(it.data.results.toMutableList())
                    Log.d("trtrtr", itemsNowPlaying.value.toString())
                    viewPageAdapter.addFragment(MoviePlayingNowFragment(itemsNowPlaying))
                }
                is DataState.Loading -> {
                }
                else -> {
                    Log.d("moviee", (it as DataState.Error).exception.toString())

                }
            }
        })
*/
/*
        getViewModel().dataStateMovieTopRated.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieTopRated> -> {
                    itemsTopRated = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MovieTopRatedFragment(itemsTopRated))

                }
                is DataState.Loading -> {
                }
                else -> {
                    Log.d("moviee", (it as DataState.Error).exception.toString())

                }
            }
        })
*/
/*
        getViewModel().dataStateMovieUpComing.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieUpComing> -> {
                    itemsUpComing = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MovieUpComingFragment(itemsUpComing))

                }
                is DataState.Loading -> {
                }
                else -> {
                    Log.d("moviee", (it as DataState.Error).exception.toString())

                }
            }
        })
*/
/*
        getViewModel().dataStateMoviePopular.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MoviePopular> -> {
                    itemsPopular = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MoviePopularFragment(itemsPopular))

                }
                is DataState.Loading -> {
                }
                else -> {
                    Log.d("moviee", (it as DataState.Error).exception.toString())

                }
            }
        })
*/
    }

    private fun setupMoviesViewPager() {
        getViewDataBinding().tpMovies.setupWithViewPager(getViewDataBinding().vpMovies, true)
        viewPageAdapter = ViewPageAdapter(childFragmentManager, mutableListOf())
        getViewDataBinding().vpMovies.adapter = viewPageAdapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_movies
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel(): MoviesViewModel {
        return moviesViewModel
    }

    private fun onClick() {
        getViewDataBinding().tvPlayingNow.setOnClickListener {
            Log.d("trtrtrtrt", getViewDataBinding().vpMovies.currentItem.toString())
            getViewDataBinding().vpMovies.currentItem = 0
        }
        getViewDataBinding().tvTopRated.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpMovies.currentItem.toString())
            getViewDataBinding().vpMovies.currentItem = 1
        }
        getViewDataBinding().tvUpComing.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpMovies.currentItem.toString())
            getViewDataBinding().vpMovies.currentItem = 2
        }
        getViewDataBinding().tvPopular.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpMovies.currentItem.toString())
            getViewDataBinding().vpMovies.currentItem = 3
        }
    }

}