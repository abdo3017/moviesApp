package com.app.movie.presentation.ui.movies.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.R
import com.app.movie.databinding.FragmentMoviePopularBinding
import com.app.movie.databinding.ItemMoviePopularBinding
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.base.ItemClickListener
import com.app.movie.presentation.base.LoadStateAdapter
import com.app.movie.presentation.ui.movies.main.MoviesViewModel
import com.app.movie.utils.BindingAdapters
import com.app.movie.utils.CenterZoomLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviePopularFragment(private val items: Flow<PagingData<MoviePopularResult>>) :
    BaseFragment<FragmentMoviePopularBinding, MoviesViewModel>() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private var visiblePosition: Int = 0
    private var lastSelectedItemBinding: ItemMoviePopularBinding? = null
    private lateinit var layoutManager: CenterZoomLayoutManager
    private lateinit var adapter: MoviePopularAdapter
    private var lastVisibleItemWhiteBoarder: ConstraintLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        onFavClick()
        setUp()
        getData()
        observeData()
        return getMRootView()
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie_popular
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel() = moviesViewModel

    private fun onFavClick() {
        getViewDataBinding().btnFav.setOnClickListener {
            if (adapter.listView[visiblePosition]!!.isFav == false) {
                getViewModel().insertFavMoviesPopular(
                    adapter.getItems(
                        visiblePosition
                    )
                )
                getViewDataBinding().isLikedByMe = true
                adapter.listView[visiblePosition]?.isFav = true
            } else {
                getViewModel().deleteFavMoviesPopular(
                    adapter.getItems(
                        visiblePosition
                    )
                )
                getViewDataBinding().isLikedByMe = false
                adapter.listView[visiblePosition]?.isFav = false
            }
        }
    }

    private fun observeData() {
        getViewModel().dataStateMoviePopular.observe(viewLifecycleOwner, {
            adapter.addFavItems(it)
        })
    }

    private fun getData() {
        getViewModel().getFavMoviesPopular()
        lifecycleScope.launch {
            items.collectLatest {
                adapter.submitData(it)
            }

        }

    }

    private fun setUp() {
        adapter = MoviePopularAdapter(onMovieItemSelected())
        //Setup recyclerView
        layoutManager = CenterZoomLayoutManager(requireContext())
        getViewDataBinding().rvMoviesPopular.layoutManager = layoutManager


        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(getViewDataBinding().rvMoviesPopular)

        getViewDataBinding().rvMoviesPopular.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (visiblePosition > -1) {
                    val visibleView: View? = layoutManager.findViewByPosition(visiblePosition)

                    visibleView?.let {
                        getViewDataBinding().isLikedByMe = adapter.listView[visiblePosition]!!.isFav
                        onFocusedItemChange(adapter.getItems(visiblePosition), it)
                    }
                }
            }
        })
        getViewDataBinding().rvMoviesPopular.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { adapter.retry() },
            footer = LoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvMoviesPopular.scrollToPosition(0) }
        }
    }


    private fun onFocusedItemChange(movie: MoviePopularResult, view: View) {
        //change card boarder color
        changeTransparentOfFocusedItem(view)
        //Change background image
        changeBackgroundImage(movie)

    }

    private fun displayMovieDetails(binding: ItemMoviePopularBinding, show: Boolean) {
        //Hide Image
        val params = binding.posterGuideline.layoutParams as ConstraintLayout.LayoutParams
        if (show) params.guidePercent = 0.0f
        else params.guidePercent = 0.6f
        binding.posterGuideline.layoutParams = params

        //Display Movie Details
        binding.isDetailsVisible = show

        //ToDo: Scale white board

        lastSelectedItemBinding = binding
    }

    private fun changeTransparentOfFocusedItem(view: View) {

        val whiteImageView = view.findViewById<ConstraintLayout>(R.id.containerLayout)
        whiteImageView.setBackgroundResource(R.drawable.round_rectangle_white)
        lastVisibleItemWhiteBoarder?.setBackgroundResource(R.drawable.round_rectangle_white_transparent)

        lastVisibleItemWhiteBoarder = whiteImageView
    }

    private fun changeBackgroundImage(movie: MoviePopularResult) {
        BindingAdapters.loadImage(getViewDataBinding().backgroundImageView, movie.posterPath)
    }

    private fun onMovieItemSelected() = ItemClickListener { position: Int, view: View ->
        //Display selected item details
        if (lastSelectedItemBinding != adapter.listView[position])
            displayMovieDetails(adapter.listView[position]!!, true)
        else {
            //Hide selected item details
            if (lastSelectedItemBinding!!.isDetailsVisible == true)
                lastSelectedItemBinding?.let {
                    displayMovieDetails(it, false)
                }
            //Display selected item details
            else lastSelectedItemBinding?.let {
                displayMovieDetails(it, true)
            }
        }
    }


}