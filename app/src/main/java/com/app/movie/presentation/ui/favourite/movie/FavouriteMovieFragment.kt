package com.app.movie.presentation.ui.favourite.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.filter
import com.app.movie.R
import com.app.movie.databinding.FragmentFavouritMovieBinding
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.base.ItemClickListener
import com.app.movie.presentation.base.LoadStateAdapter
import com.app.movie.presentation.ui.favourite.main.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavouriteMovieFragment(
    private val itemsPlayingNow: Flow<PagingData<MovieNowPlayingResultsItem>>,
    private val itemsPopular: Flow<PagingData<MoviePopularResult>>,
    private val itemsTopRated: Flow<PagingData<MovieTopRatedResult>>,
    private val itemsUpComing: Flow<PagingData<MovieUpComingResult>>
) :
    BaseFragment<FragmentFavouritMovieBinding, FavouriteViewModel>() {
    private val favouriteViewModel: FavouriteViewModel by viewModels()
    private lateinit var favMoviePlayingNowAdapter: FavMoviePlayingNowAdapter
    private lateinit var favMoviePopularAdapter: FavMoviePopularAdapter
    private lateinit var favMovieTopRatedAdapter: FavMovieTopRatedAdapter
    private lateinit var favMovieUpComingAdapter: FavMovieUpComingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setUp()
        getData(1, 2, 3, 4)
        observeData()
        return getMRootView()
    }

    override val layoutId: Int
        get() = R.layout.fragment_favourit_movie
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel() = favouriteViewModel

    private fun getData(num1: Int, num2: Int, num3: Int, num4: Int) {
        lifecycleScope.launch {
            if (num1 == 1) {
                val favMovie1 = async {
                    getViewModel().getFavMoviesNowPlaying()
                }
                favMovie1.await()
            }
            if (num2 == 2) {
                val favMovie3 = async {
                    getViewModel().getFavMoviesTopRated()
                }
                favMovie3.await()
            }
            if (num3 == 3) {
                val favMovie2 = async {
                    getViewModel().getFavMoviesPopular()
                }
                favMovie2.await()
            }
            if (num4 == 4) {
                val favMovie4 = async {
                    getViewModel().getFavMoviesUpComing()
                }
                favMovie4.await()
            }
        }
    }

    private fun observeData() {
        favMoviePlayingNowAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingMoviePlayingNow = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingMoviePlayingNow = true
                    getViewDataBinding().loadingMoviePlayingNowLayout.stopShimmer()
                    getViewDataBinding().loadingMoviePlayingNowLayout.hideShimmer()
                    getViewDataBinding().loadingMoviePlayingNowLayout.visibility = View.GONE
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

        getViewModel().dataStateMovieNowPlaying.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsPlayingNow.collectLatest { data ->
                    val listIds = list.map { it.id }
                    Log.d("movieeeeeeeeee", listIds.size.toString())
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingMoviePlayingNow = true
                        getViewDataBinding().loadingMoviePlayingNowLayout.visibility = View.GONE
                    } else
                        favMoviePlayingNowAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        favMovieTopRatedAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingMovieTopRated = false
                }
                is LoadState.NotLoading -> {
                    getViewDataBinding().loadingMovieTopRatedLayout.stopShimmer()
                    getViewDataBinding().loadingMovieTopRatedLayout.hideShimmer()
                    getViewDataBinding().loadingMovieTopRatedLayout.visibility = View.GONE
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

        getViewModel().dataStateMovieTopRated.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsTopRated.collectLatest { data ->
                    val listIds = list.map { it.id }
                    Log.d("hereeeeeee", listIds.isNullOrEmpty().toString())

                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingMovieTopRated = true
                        getViewDataBinding().loadingMovieTopRatedLayout.visibility = View.GONE
                    } else
                        favMovieTopRatedAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        favMoviePopularAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingMoviePopular = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingMoviePopular = false
                    getViewDataBinding().loadingMoviePopularLayout.stopShimmer()
                    getViewDataBinding().loadingMoviePopularLayout.hideShimmer()
                    getViewDataBinding().loadingMoviePopularLayout.visibility = View.GONE
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

        getViewModel().dataStateMoviePopular.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsPopular.collectLatest { data ->
                    val listIds = list.map { it.id }
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingMoviePopular = true
                        getViewDataBinding().loadingMoviePopularLayout.visibility = View.GONE
                    } else
                        favMoviePopularAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        favMovieUpComingAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingMovieUpComing = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingMovieUpComing = true
                    getViewDataBinding().loadingMovieUpComingLayout.stopShimmer()
                    getViewDataBinding().loadingMovieUpComingLayout.hideShimmer()
                    getViewDataBinding().loadingMovieUpComingLayout.visibility = View.GONE
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

        getViewModel().dataStateMovieUpComing.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsUpComing.collectLatest { data ->
                    val listIds = list.map { it.id }
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingMovieUpComing = true
                        getViewDataBinding().loadingMovieUpComingLayout.visibility = View.GONE
                    } else
                        favMovieUpComingAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })
    }

    private fun setUp() {
        favMoviePlayingNowAdapter = FavMoviePlayingNowAdapter(onMoviePlayingNowItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvPlayingNow.adapter =
            favMoviePlayingNowAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favMoviePlayingNowAdapter.retry() },
                footer = LoadStateAdapter { favMoviePlayingNowAdapter.retry() }
            )

        lifecycleScope.launch {
            favMoviePlayingNowAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvPlayingNow.scrollToPosition(0) }
        }

        favMovieTopRatedAdapter = FavMovieTopRatedAdapter(onMovieTopRatedItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvTopRated.adapter =
            favMovieTopRatedAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favMovieTopRatedAdapter.retry() },
                footer = LoadStateAdapter { favMovieTopRatedAdapter.retry() }
            )

        lifecycleScope.launch {
            favMovieTopRatedAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvTopRated.scrollToPosition(0) }
        }

        favMoviePopularAdapter = FavMoviePopularAdapter(onMoviePopularItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvPopular.adapter =
            favMoviePopularAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favMoviePopularAdapter.retry() },
                footer = LoadStateAdapter { favMoviePopularAdapter.retry() }
            )

        lifecycleScope.launch {
            favMoviePopularAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvPopular.scrollToPosition(0) }
        }

        favMovieUpComingAdapter = FavMovieUpComingAdapter(onMovieUpComingItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvUpComing.adapter =
            favMovieUpComingAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favMovieUpComingAdapter.retry() },
                footer = LoadStateAdapter { favMovieUpComingAdapter.retry() }
            )

        lifecycleScope.launch {
            favMovieUpComingAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvUpComing.scrollToPosition(0) }
        }
    }

    private fun onMoviePlayingNowItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
//                    favMoviePlayingNowAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favMoviePlayingNowAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavMoviesNowPlaying(
                    favMoviePlayingNowAdapter.getItems(
                        position
                    )
                )
                //favMoviePlayingNowAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavMoviesNowPlaying(
                    favMoviePlayingNowAdapter.getItems(
                        position
                    )
                )
                favMoviePlayingNowAdapter.notifyDataSetChanged()
                favMoviePlayingNowAdapter.listView.remove(position)
                getData(1, 1, 1, 1)
            }
        }
    }

    private fun onMovieTopRatedItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
//                    favMovieTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favMovieTopRatedAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavMoviesTopRated(
                    favMovieTopRatedAdapter.getItems(
                        position
                    )
                )
                favMovieTopRatedAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavMoviesTopRated(
                    favMovieTopRatedAdapter.getItems(
                        position
                    )
                )
                getData(2, 2, 2, 2)
            }
        }
    }

    private fun onMoviePopularItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
//                    favMovieTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favMoviePopularAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavMoviesPopular(
                    favMoviePopularAdapter.getItems(
                        position
                    )
                )
                favMoviePopularAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavMoviesPopular(
                    favMoviePopularAdapter.getItems(
                        position
                    )
                )
                getData(3, 3, 3, 3)
            }
        }
    }

    private fun onMovieUpComingItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
//                    favMovieTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favMovieUpComingAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavMoviesUpComing(
                    favMovieUpComingAdapter.getItems(
                        position
                    )
                )
                favMovieUpComingAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavMoviesUpComing(
                    favMovieUpComingAdapter.getItems(
                        position
                    )
                )
                getData(4, 4, 4, 4)
            }
        }

    }
}
