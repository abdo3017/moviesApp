package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.movie.R
import com.app.movie.databinding.HomeFragmentBinding
import com.app.movie.presentation.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragment :
    BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return getMRootView()
    }

    override fun getViewModel(): HomeViewModel {
        return viewmodel
    }

    override val layoutId: Int
        get() = R.layout.home_fragment
    override val bindingVariable: Int
        get() = TODO("Not yet implemented")

}