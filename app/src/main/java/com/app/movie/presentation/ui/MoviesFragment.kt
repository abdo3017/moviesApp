package com.app.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.movie.databinding.FragmentMoviesBinding
import com.app.movie.presentation.base.BaseFragment
import com.app.movie.presentation.ui.homepage.HomeViewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding, HomeViewModel>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        return getMRootView()
    }

    override val layoutId: Int
        get() = TODO("Not yet implemented")
    override val bindingVariableId: Int
        get() = TODO("Not yet implemented")
    override val bindingVariableValue: Any
        get() = TODO("Not yet implemented")

    override fun getViewModel(): HomeViewModel {
        TODO("Not yet implemented")
    }


}