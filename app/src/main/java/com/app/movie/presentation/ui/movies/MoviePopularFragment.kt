package com.app.movie.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.R
import com.app.movie.databinding.FragmentMoviePopularBinding
import com.app.movie.databinding.ItemMoviePopularBinding
import com.app.movie.datasource.network.models.MoviePopularResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.utils.BindingAdapters
import com.app.movie.utils.CenterZoomLayoutManager


class MoviePopularFragment(private val items: MutableLiveData<MutableList<MoviePopularResult>>) :
    BaseFragment<FragmentMoviePopularBinding, Any>(),
    MoviePopularAdapter.MoviesInteraction {

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
        setUp()
        return getMRootView()
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie_popular
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel(): Any {
        return Any()
    }


    private fun setUp() {
        //Setup recyclerView
        layoutManager = CenterZoomLayoutManager(requireContext())
        getViewDataBinding().rvMoviesPopular.layoutManager = layoutManager


        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(getViewDataBinding().rvMoviesPopular)

        getViewDataBinding().rvMoviesPopular.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visiblePosition: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (visiblePosition > -1) {
                    val visibleView: View? = layoutManager.findViewByPosition(visiblePosition)

                    visibleView?.let {
                        onFocusedItemChange(items.value!![visiblePosition], it)
                    }
                }
            }
        })
        adapter = MoviePopularAdapter(items.value!!, this)
        getViewDataBinding().rvMoviesPopular.adapter = adapter
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

    override fun onMovieItemSelected(
        position: Int,
        item: MoviePopularResult,
        binding: ItemMoviePopularBinding
    ) {
        //Hide last selected item details
        lastSelectedItemBinding?.let { displayMovieDetails(it, false) }

        //Display selected item details
        if (lastSelectedItemBinding != binding)
            displayMovieDetails(binding, true)
    }

}