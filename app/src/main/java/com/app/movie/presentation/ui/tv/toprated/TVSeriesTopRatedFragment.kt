package com.app.movie.presentation.ui.tv.toprated

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
import com.app.movie.databinding.FragmentTVTopRatedBinding
import com.app.movie.databinding.ItemTVTopRatedBinding
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.base.ItemClickListener
import com.app.movie.presentation.base.LoadStateAdapter
import com.app.movie.presentation.ui.tv.main.TVSeriesViewModel
import com.app.movie.utils.BindingAdapters
import com.app.movie.utils.CenterZoomLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TVSeriesTopRatedFragment(private val items: Flow<PagingData<TVSeriesTopRatedResult>>) :
    BaseFragment<FragmentTVTopRatedBinding, TVSeriesViewModel>() {
    private val tvSeriesViewModel: TVSeriesViewModel by viewModels()
    private var visiblePosition: Int = 0
    private var lastSelectedItemBinding: ItemTVTopRatedBinding? = null
    private lateinit var layoutManager: CenterZoomLayoutManager
    private var lastVisibleItemWhiteBoarder: ConstraintLayout? = null
    private lateinit var adapter: TVSeriesTopRatedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        setUp()
        getData()
        observeData()
        onFavClick()
        return getMRootView()
    }

    private fun onFavClick() {
        getViewDataBinding().btnFav.setOnClickListener {
            if (adapter.listView[visiblePosition]!!.isFav == false) {
                getViewModel().insertFavTVSeriesTopRated(
                    adapter.getItems(
                        visiblePosition
                    )
                )
                getViewDataBinding().isLikedByMe = true
                adapter.listView[visiblePosition]?.isFav = true
            } else {
                getViewModel().deleteFavTVSeriesTopRated(
                    adapter.getItems(
                        visiblePosition
                    )
                )
                getViewDataBinding().isLikedByMe = false
                adapter.listView[visiblePosition]?.isFav = false
            }
        }
    }

    private fun getData() {
        lifecycleScope.launch {
            items.collectLatest {
                adapter.submitData(it)
            }
            val favTV = async {
                getViewModel().getFavTVSeriesTopRated()
            }
            favTV.await()
        }

    }

    private fun observeData() {
        getViewModel().dataStateTVSeriesTopRated.observe(viewLifecycleOwner, {
            adapter.addFavItems(it)
        })
    }

    private fun setUp() {
        adapter = TVSeriesTopRatedAdapter(onItemSelected())
        //Setup recyclerView
        layoutManager = CenterZoomLayoutManager(requireContext())
        getViewDataBinding().rvTVSeriesTopRated.layoutManager = layoutManager


        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(getViewDataBinding().rvTVSeriesTopRated)

        getViewDataBinding().rvTVSeriesTopRated.addOnScrollListener(object :
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
        getViewDataBinding().rvTVSeriesTopRated.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { adapter.retry() },
            footer = LoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { getViewDataBinding().rvTVSeriesTopRated.scrollToPosition(0) }
        }
    }

    private fun onItemSelected() = ItemClickListener { position: Int, view: View ->
        //Display selected item details
        if (lastSelectedItemBinding != adapter.listView[position])
            displayTVDetails(adapter.listView[position]!!, true)
        else {
            //Hide selected item details
            if (lastSelectedItemBinding!!.isDetailsVisible == true)
                lastSelectedItemBinding?.let {
                    displayTVDetails(it, false)
                }
            //Display selected item details
            else lastSelectedItemBinding?.let {
                displayTVDetails(it, true)
            }
        }
    }


    private fun onFocusedItemChange(item: TVSeriesTopRatedResult, view: View) {
        //change card boarder color
        changeTransparentOfFocusedItem(view)
        //Change background image
        changeBackgroundImage(item)

    }

    private fun displayTVDetails(binding: ItemTVTopRatedBinding, show: Boolean) {
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

    private fun changeBackgroundImage(movie: TVSeriesTopRatedResult) {
        BindingAdapters.loadImage(getViewDataBinding().backgroundImageView, movie.posterPath)
    }

    override val layoutId: Int
        get() = R.layout.fragment_t_v_top_rated
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel() = tvSeriesViewModel


}