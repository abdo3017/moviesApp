package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.app.movie.R
import com.app.movie.databinding.FragmentHomeBinding
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.TVSeriesTopRatedResult
import com.app.movie.domain.models.TVSeriesTopRated
import com.app.movie.domain.state.DataState
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.ui.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeMoviePlayingNowAdapter.MovieInteraction,
    TVSeriesTopRatedAdapter.TVSeriesInteraction {

    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter = HomeMoviePlayingNowAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setViews()
        getData()
        observeData()
        return getMRootView()
    }


    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    private fun getData() {
        lifecycleScope.launch {
            getViewModel().getMoviesNowPlaying().collectLatest {
                adapter.submitData(it)
                getViewDataBinding().isLoadingMovie = false
                getViewDataBinding().loadingMovieLayout.stopShimmer()
                getViewDataBinding().loadingMovieLayout.hideShimmer()
            }
        }
        getViewModel().getTVSeriesTopRated()

    }

    private fun setViews() {

        getViewDataBinding().rvMoviesPlayingNow.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { adapter.retry() },
            footer = LoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvMoviesPlayingNow.scrollToPosition(0) }
        }
        getViewDataBinding().rvTVSeriesTopRated.adapter =
            TVSeriesTopRatedAdapter(mutableListOf(), this)
    }

    private fun observeData() {
//        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, {
//            when (it) {
//                is DataState.Success<MovieNowPlaying> -> {
//                    (getViewDataBinding().rvMoviesPlayingNow.adapter as HomeMoviePlayingNowAdapter).addItems(
//                        items = it.data.results
//                    )
//                    getViewDataBinding().isLoadingMovie = false
//                    getViewDataBinding().loadingMovieLayout.stopShimmer()
//                    getViewDataBinding().loadingMovieLayout.hideShimmer()
//
//                }
//                is DataState.Error<*> -> {
//                    Log.d("movie", it.exception.toString())
//                }
//                is DataState.Loading -> {
//                    getViewDataBinding().isLoadingMovie = true
//                }
//            }
//        })
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
                is DataState.Loading -> {
                    getViewDataBinding().isLoadingTV = true

                }
                else -> {
                    Log.d("moviee", (it as DataState.Error).exception.toString())

                }
            }
        })
    }

    override val layoutId: Int
        get() = R.layout.fragment_home
    override val bindingVariableId: Int
        get() = BR.homeViewModel
    override val bindingVariableValue: Any
        get() = getViewModel()

    override fun onMovieItemSelected(item: MovieNowPlayingResultsItem) {
        val extras = FragmentNavigatorExtras(
            imageViewHome to "imageViewHome"
        )
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                item
            ), extras
        )
    }

    override fun onTVSeriesItemSelected(item: TVSeriesTopRatedResult) {
        val extras = FragmentNavigatorExtras(
            imageViewHome to "imageViewHome"
        )
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToTVSeriesDetailsFragment(
                item
            ), extras
        )
    }


}