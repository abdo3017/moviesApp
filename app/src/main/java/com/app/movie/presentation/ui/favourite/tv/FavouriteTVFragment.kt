package com.app.movie.presentation.ui.favourite.tv

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
import com.app.movie.databinding.FragmentFavouritTvBinding
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
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
class FavouriteTVFragment(
    private val itemsOnTheAir: Flow<PagingData<TVSeriesOnTheAirResult>>,
    private val itemsPopular: Flow<PagingData<TVSeriesPopularResult>>,
    private val itemsTopRated: Flow<PagingData<TVSeriesTopRatedResult>>,
    private val itemsAiringToday: Flow<PagingData<TVSeriesAiringTodayResult>>
) :
    BaseFragment<FragmentFavouritTvBinding, FavouriteViewModel>() {
    private val favouriteViewModel: FavouriteViewModel by viewModels()
    private lateinit var favTVOnTheAirAdapter: FavTVOnTheAirAdapter
    private lateinit var favTVPopularAdapter: FavTVPopularAdapter
    private lateinit var favTVTopRatedAdapter: FavTVTopRatedAdapter
    private lateinit var fvTVAiringTodayAdapter: FavTVAiringTodayAdapter
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
        get() = R.layout.fragment_favourit_tv
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel() = favouriteViewModel

    private fun getData(num1: Int, num2: Int, num3: Int, num4: Int) {
        lifecycleScope.launch {
            if (num1 == 1) {
                val favTV1 = async {
                    getViewModel().getFavTVSeriesOnTheAir()
                }
                favTV1.await()
            }
            if (num3 == 3) {
                val favTV2 = async {
                    getViewModel().getFavTVSeriesPopular()
                }
                favTV2.await()
            }
            if (num2 == 2) {
                val favTV3 = async {
                    getViewModel().getFavTVSeriesTopRated()
                }
                favTV3.await()
            }
            if (num4 == 4) {
                val favTV4 = async {
                    getViewModel().getFavTVSeriesAiringToday()
                }
                favTV4.await()
            }
        }
    }

    private fun observeData() {
        favTVOnTheAirAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingTVOnTheAir = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingTVOnTheAir = true
                    getViewDataBinding().loadingTVOnTheAirLayout.stopShimmer()
                    getViewDataBinding().loadingTVOnTheAirLayout.hideShimmer()
                    getViewDataBinding().loadingTVOnTheAirLayout.visibility = View.GONE
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

        getViewModel().dataStateTVSeriesOnTheAir.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsOnTheAir.collectLatest { data ->
                    val listIds = list.map { it.id }
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingTVOnTheAir = true
                        getViewDataBinding().loadingTVOnTheAirLayout.visibility = View.GONE
                    } else
                        favTVOnTheAirAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        favTVTopRatedAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingTVTopRated = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingTVTopRated = true
                    getViewDataBinding().loadingTVTopRatedLayout.stopShimmer()
                    getViewDataBinding().loadingTVTopRatedLayout.hideShimmer()
                    getViewDataBinding().loadingTVTopRatedLayout.visibility = View.GONE
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

        getViewModel().dataStateTVSeriesTopRated.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsTopRated.collectLatest { data ->
                    val listIds = list.map { it.id }
                    Log.d("hereeeeeee", listIds.isNullOrEmpty().toString())

                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingTVTopRated = true
                        getViewDataBinding().loadingTVTopRatedLayout.visibility = View.GONE
                    } else
                        favTVTopRatedAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        favTVPopularAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingTVPopular = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingTVPopular = true
                    getViewDataBinding().loadingTVPopularLayout.stopShimmer()
                    getViewDataBinding().loadingTVPopularLayout.hideShimmer()
                    getViewDataBinding().loadingTVPopularLayout.visibility = View.GONE
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

        getViewModel().dataStateTVSeriesPopular.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsPopular.collectLatest { data ->
                    val listIds = list.map { it.id }
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingTVPopular = true
                        getViewDataBinding().loadingTVPopularLayout.visibility = View.GONE
                    } else
                        favTVPopularAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })

        fvTVAiringTodayAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    getViewDataBinding().isLoadingTVAiringToday = false
                }
                is LoadState.NotLoading -> {
                    //getViewDataBinding().isLoadingTVAiringToday = true
                    getViewDataBinding().loadingTVAiringTodayLayout.stopShimmer()
                    getViewDataBinding().loadingTVAiringTodayLayout.hideShimmer()
                    getViewDataBinding().loadingTVAiringTodayLayout.visibility = View.GONE
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

        getViewModel().dataStateTVSeriesAiringToday.observe(viewLifecycleOwner, { list ->
            lifecycleScope.launch {
                itemsAiringToday.collectLatest { data ->
                    val listIds = list.map { it.id }
                    if (listIds.isNullOrEmpty()) {
                        getViewDataBinding().isLoadingTVAiringToday = true
                        getViewDataBinding().loadingTVAiringTodayLayout.visibility = View.GONE
                    } else
                        fvTVAiringTodayAdapter.submitData(data.filter { it.id in listIds })
                }
            }
        })
    }

    private fun setUp() {
        favTVOnTheAirAdapter = FavTVOnTheAirAdapter(onTVOnTheAirItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvOnTheAir.adapter =
            favTVOnTheAirAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favTVOnTheAirAdapter.retry() },
                footer = LoadStateAdapter { favTVOnTheAirAdapter.retry() }
            )

        lifecycleScope.launch {
            favTVOnTheAirAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvOnTheAir.scrollToPosition(0) }
        }

        favTVTopRatedAdapter = FavTVTopRatedAdapter(onTVTopRatedItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvTopRated.adapter =
            favTVTopRatedAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favTVTopRatedAdapter.retry() },
                footer = LoadStateAdapter { favTVTopRatedAdapter.retry() }
            )

        lifecycleScope.launch {
            favTVTopRatedAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvTopRated.scrollToPosition(0) }
        }

        favTVPopularAdapter = FavTVPopularAdapter(onTVPopularItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvPopular.adapter =
            favTVPopularAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { favTVPopularAdapter.retry() },
                footer = LoadStateAdapter { favTVPopularAdapter.retry() }
            )

        lifecycleScope.launch {
            favTVPopularAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvPopular.scrollToPosition(0) }
        }

        fvTVAiringTodayAdapter = FavTVAiringTodayAdapter(onTVAiringTodayItemSelected())
        //Setup recyclerView
        getViewDataBinding().rvAiringToday.adapter =
            fvTVAiringTodayAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { fvTVAiringTodayAdapter.retry() },
                footer = LoadStateAdapter { fvTVAiringTodayAdapter.retry() }
            )

        lifecycleScope.launch {
            fvTVAiringTodayAdapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvAiringToday.scrollToPosition(0) }
        }
    }

    private fun onTVOnTheAirItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToTVDetailsFragment(
//                    favTVOnTheAirAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favTVOnTheAirAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavTVSeriesOnTheAir(
                    favTVOnTheAirAdapter.getItems(
                        position
                    )
                )
                favTVOnTheAirAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavTVSeriesOnTheAir(
                    favTVOnTheAirAdapter.getItems(
                        position
                    )
                )
                getData(1, 1, 1, 1)
            }
        }
    }

    private fun onTVTopRatedItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToTVDetailsFragment(
//                    favTVTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favTVTopRatedAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavTVSeriesTopRated(
                    favTVTopRatedAdapter.getItems(
                        position
                    )
                )
                favTVTopRatedAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavTVSeriesTopRated(
                    favTVTopRatedAdapter.getItems(
                        position
                    )
                )
                getData(2, 2, 2, 2)
            }
        }
    }

    private fun onTVPopularItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToTVDetailsFragment(
//                    favTVTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (favTVPopularAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavTVSeriesPopular(
                    favTVPopularAdapter.getItems(
                        position
                    )
                )
                favTVPopularAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavTVSeriesPopular(
                    favTVPopularAdapter.getItems(
                        position
                    )
                )
                getData(3, 3, 3, 3)
            }
        }
    }

    private fun onTVAiringTodayItemSelected() = ItemClickListener { position: Int, view: View ->
        if (view.id == R.id.imageViewHome) {
            val extras = FragmentNavigatorExtras(
                imageViewHome to "imageViewHome"
            )
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToTVDetailsFragment(
//                    favTVTopRatedAdapter.getItems(position)
//                ), extras
//            )
        } else {
            if (fvTVAiringTodayAdapter.listView[position]!!.isFav == false) {
                getViewModel().insertFavTVSeriesAiringToday(
                    fvTVAiringTodayAdapter.getItems(
                        position
                    )
                )
                fvTVAiringTodayAdapter.listView[position]?.isFav = true
            } else {
                getViewModel().deleteFavTVSeriesAiringToday(
                    fvTVAiringTodayAdapter.getItems(
                        position
                    )
                )
                getData(4, 4, 4, 4)
            }
        }

    }
}
