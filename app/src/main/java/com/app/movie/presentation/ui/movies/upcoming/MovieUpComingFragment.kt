package com.app.movie.presentation.ui.movies.upcoming

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
import com.app.movie.databinding.FragmentMovieUpComingBinding
import com.app.movie.databinding.ItemMovieUpComingBinding
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.base.ItemClickListener
import com.app.movie.presentation.base.LoadStateAdapter
import com.app.movie.presentation.ui.movies.main.MoviesViewModel
import com.app.movie.utils.BindingAdapters
import com.app.movie.utils.CenterZoomLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieUpComingFragment(private val items: Flow<PagingData<MovieUpComingResult>>) :
    BaseFragment<FragmentMovieUpComingBinding, MoviesViewModel>() {
    private val moviesViewModel: MoviesViewModel by viewModels()

    private var lastSelectedItemBinding: ItemMovieUpComingBinding? = null
    private lateinit var layoutManager: CenterZoomLayoutManager
    private lateinit var adapter: MovieUpComingAdapter
    private var lastVisibleItemWhiteBoarder: ConstraintLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setUp()
        getData()
        observeData()
        return getMRootView()
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie_up_coming
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel() = moviesViewModel


    private fun getData() {
        lifecycleScope.launch {
            items.collectLatest {
                adapter.submitData(it)
            }
            val favMovie = async {
                getViewModel().getFavMoviesUpComing()
            }
            favMovie.await()
        }

    }

    private fun observeData() {
        getViewModel().dataStateMovieUpComing.observe(viewLifecycleOwner, {
            adapter.addFavItems(it)
        })
    }

    private fun setUp() {
        adapter = MovieUpComingAdapter(onMovieItemSelected())
        //Setup recyclerView
        layoutManager = CenterZoomLayoutManager(requireContext())
        getViewDataBinding().rvMoviesUpComing.layoutManager = layoutManager


        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(getViewDataBinding().rvMoviesUpComing)

        getViewDataBinding().rvMoviesUpComing.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visiblePosition: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (visiblePosition > -1) {
                    val visibleView: View? = layoutManager.findViewByPosition(visiblePosition)

                    visibleView?.let {
                        onFocusedItemChange(adapter.getItems(visiblePosition), it)
                    }
                }
            }
        })
        getViewDataBinding().rvMoviesUpComing.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { adapter.retry() },
            footer = LoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvMoviesUpComing.scrollToPosition(0) }
        }
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

    private fun onFocusedItemChange(movie: MovieUpComingResult, view: View) {
        //change card boarder color
        changeTransparentOfFocusedItem(view)
        //Change background image
        changeBackgroundImage(movie)

    }

    private fun displayMovieDetails(binding: ItemMovieUpComingBinding, show: Boolean) {
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

    private fun changeBackgroundImage(movie: MovieUpComingResult) {
        BindingAdapters.loadImage(getViewDataBinding().backgroundImageView, movie.posterPath)
    }

}