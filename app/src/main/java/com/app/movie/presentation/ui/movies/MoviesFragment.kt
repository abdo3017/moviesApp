package com.app.movie.presentation.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.app.movie.R
import com.app.movie.databinding.FragmentMoviesBinding
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.MoviePopularResult
import com.app.movie.datasource.network.models.MovieTopRatedResult
import com.app.movie.datasource.network.models.MovieUpComingResult
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.domain.models.MoviePopular
import com.app.movie.domain.models.MovieTopRated
import com.app.movie.domain.models.MovieUpComing
import com.app.movie.domain.state.DataState
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.utils.ViewPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private var itemsNowPlaying: MutableLiveData<MutableList<MovieNowPlayingResultsItem>> =
        MutableLiveData()
    private var itemsTopRated: MutableLiveData<MutableList<MovieTopRatedResult>> =
        MutableLiveData()
    private var itemsUpComing: MutableLiveData<MutableList<MovieUpComingResult>> =
        MutableLiveData()

    private var itemsPopular: MutableLiveData<MutableList<MoviePopularResult>> =
        MutableLiveData()

    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        onClick()
        fetchData()
        observeData()
        setupMoviesViewPager()
        return getMRootView()
    }

    private fun fetchData() {
//        getViewModel().getMoviesNowPlaying()
//        getViewModel().getMoviesTopRated()
//        getViewModel().getMoviesUpComing()
//        getViewModel().getMoviesPopular()
        runBlocking<Unit> {
            val one = async(start = CoroutineStart.LAZY) { getViewModel().getMoviesNowPlaying() }
            val two = async(start = CoroutineStart.LAZY) { getViewModel().getMoviesTopRated() }
            val three = async(start = CoroutineStart.LAZY) { getViewModel().getMoviesUpComing() }
            val four = async(start = CoroutineStart.LAZY) { getViewModel().getMoviesPopular() }
            // some computation
            one.start() // start the first one
            two.start()
            three.start()
            four.start()
        }
    }

    private fun observeData() {
        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieNowPlaying> -> {
                    itemsNowPlaying = MutableLiveData(it.data.results.toMutableList())
                    Log.d("trtrtr", itemsNowPlaying.value.toString())
                    viewPageAdapter.addFragment(MoviePlayingNowFragment(itemsNowPlaying))
                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())
                }
                is DataState.Loading -> {
                }
            }
        })
        getViewModel().dataStateMovieTopRated.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieTopRated> -> {
                    itemsTopRated = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MovieTopRatedFragment(itemsTopRated))

                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())
                }
                is DataState.Loading -> {
                }
            }
        })
        getViewModel().dataStateMovieUpComing.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MovieUpComing> -> {
                    itemsUpComing = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MovieUpComingFragment(itemsUpComing))

                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())
                }
                is DataState.Loading -> {
                }
            }
        })
        getViewModel().dataStateMoviePopular.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Success<MoviePopular> -> {
                    itemsPopular = MutableLiveData(it.data.results.toMutableList())
                    viewPageAdapter.addFragment(MoviePopularFragment(itemsPopular))

                }
                is DataState.Error<*> -> {
                    Log.d("movie", it.exception.toString())
                }
                is DataState.Loading -> {
                }
            }
        })
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