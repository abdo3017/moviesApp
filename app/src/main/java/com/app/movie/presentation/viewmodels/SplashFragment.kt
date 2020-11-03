package com.app.movie.presentation.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.movie.databinding.FragmentSplashBinding
import com.app.movie.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getMRootView()
    }

    //
    override fun getViewModel(): SplashViewModel {
        TODO("Not yet implemented")
    }

    override val bindingVariableId: Int
        get() = TODO("Not yet implemented")
    override val bindingVariableValue: Any
        get() = TODO("Not yet implemented")
    override val layoutId: Int
        get() = TODO("Not yet implemented")


}