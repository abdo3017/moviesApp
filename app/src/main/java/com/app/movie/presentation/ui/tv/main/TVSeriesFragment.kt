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
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.ui.tv.airingtoday.TVSeriesAiringTodayFragment
import com.app.movie.presentation.ui.tv.ontheair.TVSeriesOnTheAirFragment
import com.app.movie.presentation.ui.tv.popular.TVSeriesPopularFragment
import com.app.movie.presentation.ui.tv.toprated.TVSeriesTopRatedFragment
import com.app.movie.utils.ViewPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TVSeriesFragment : BaseFragment<FragmentTVBinding, TVSeriesViewModel>() {

    private val tvSeriesViewModel: TVSeriesViewModel by viewModels()
    private lateinit var itemsOnTheAir: Flow<PagingData<TVSeriesOnTheAirResult>>
    private lateinit var itemsTopRated: Flow<PagingData<TVSeriesTopRatedResult>>
    private lateinit var itemsAiringToday: Flow<PagingData<TVSeriesAiringTodayResult>>
    private lateinit var itemsPopular: Flow<PagingData<TVSeriesPopularResult>>
    private lateinit var viewPageAdapter: ViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setupViewPager()
        fetchData()
        onClick()
        return getMRootView()
    }

    private fun fetchData() {
        runBlocking<Unit> {
            val one = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesOnTheAir().apply {
                    itemsOnTheAir = this
                    viewPageAdapter.addFragment(TVSeriesOnTheAirFragment(itemsOnTheAir))

                }
            }
            val two = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesTopRated().apply {
                    itemsTopRated = this
                    viewPageAdapter.addFragment(TVSeriesTopRatedFragment(itemsTopRated))
                }
            }
            val three = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesAiringToday().apply {
                    itemsAiringToday = this
                    viewPageAdapter.addFragment(TVSeriesAiringTodayFragment(itemsAiringToday))
                }
            }
            val four = async(start = CoroutineStart.LAZY) {
                getViewModel().getTVSeriesPopular().apply {
                    itemsPopular = this
                    viewPageAdapter.addFragment(TVSeriesPopularFragment(itemsPopular))
                }
            }
            // some computation
            one.start() // start the first one
            two.start()
            three.start()
            four.start()
        }
    }


    private fun setupViewPager() {
        getViewDataBinding().tpTVSeries.setupWithViewPager(getViewDataBinding().vpTVSeries, true)
        viewPageAdapter = ViewPageAdapter(childFragmentManager, mutableListOf())
        getViewDataBinding().vpTVSeries.adapter = viewPageAdapter
    }

    override val layoutId: Int
        get() = R.layout.fragment_t_v
    override val bindingVariableId: Int
        get() = 0
    override val bindingVariableValue: Any
        get() = Any()

    override fun getViewModel(): TVSeriesViewModel {
        return tvSeriesViewModel
    }

    private fun onClick() {
        getViewDataBinding().tvOnTheAir.setOnClickListener {
            Log.d("trtrtrtrt", getViewDataBinding().vpTVSeries.currentItem.toString())
            getViewDataBinding().vpTVSeries.currentItem = 0
        }
        getViewDataBinding().tvTopRated.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpTVSeries.currentItem.toString())
            getViewDataBinding().vpTVSeries.currentItem = 1
        }
        getViewDataBinding().tvAiringToday.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpTVSeries.currentItem.toString())
            getViewDataBinding().vpTVSeries.currentItem = 2
        }
        getViewDataBinding().tvPopular.setOnClickListener {
            Log.d("teto", getViewDataBinding().vpTVSeries.currentItem.toString())
            getViewDataBinding().vpTVSeries.currentItem = 3
        }
    }

}