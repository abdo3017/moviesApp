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
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.base.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
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
    private val moviePlayingNowAdapter = HomeMoviePlayingNowAdapter(this)
    private val tvSeriesTopRatedAdapter = TVSeriesTopRatedAdapter(this)

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
            val movie = async {
                getViewModel().getMoviesNowPlaying().collectLatest {
                    moviePlayingNowAdapter.submitData(it)
                }
            }
            val tvSeries = async {
                getViewModel().getTVSeriesTopRated().collectLatest {
                    tvSeriesTopRatedAdapter.submitData(it)
                }
            }
            movie.await()
            tvSeries.await()
        }
    }


    private fun setViews() {

        getViewDataBinding().rvMoviesPlayingNow.adapter =
            moviePlayingNowAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { moviePlayingNowAdapter.retry() },
                footer = LoadStateAdapter { moviePlayingNowAdapter.retry() }
            )
        lifecycleScope.launch {
            moviePlayingNowAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvMoviesPlayingNow.scrollToPosition(0) }
        }
        getViewDataBinding().rvTVSeriesTopRated.adapter =
            tvSeriesTopRatedAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { tvSeriesTopRatedAdapter.retry() },
                footer = LoadStateAdapter { tvSeriesTopRatedAdapter.retry() }
            )
        lifecycleScope.launch {
            tvSeriesTopRatedAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvTVSeriesTopRated.scrollToPosition(0) }
        }
    }

    private fun observeData() {
        moviePlayingNowAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingMovie = true
                }
                is LoadState.NotLoading -> {
                    getViewDataBinding().isLoadingMovie = false
                    getViewDataBinding().loadingMovieLayout.stopShimmer()
                    getViewDataBinding().loadingMovieLayout.hideShimmer()
                }
                is LoadState.Error -> {
                    val errorState = loadState.source.append as? LoadState.Error
                        ?: loadState.source.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    Log.d("movie", errorState!!.error.toString())

                }
            }
        }

        tvSeriesTopRatedAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingTV = true
                }
                is LoadState.NotLoading -> {
                    getViewDataBinding().isLoadingTV = false
                    getViewDataBinding().loadingTVLayout.stopShimmer()
                    getViewDataBinding().loadingTVLayout.hideShimmer()
                }
                is LoadState.Error -> {
                    val errorState = loadState.source.append as? LoadState.Error
                        ?: loadState.source.prepend as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    Log.d("movie", errorState!!.error.toString())

                }
            }
        }
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