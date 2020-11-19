package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.transition.TransitionInflater
import com.app.movie.R
import com.app.movie.databinding.FragmentTVSeriesDetailsBinding
import com.app.movie.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TVSeriesDetailsFragment : BaseFragment<FragmentTVSeriesDetailsBinding, HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return getMRootView()

    }

    override val layoutId: Int
        get() = R.layout.fragment_t_v_series_details
    override val bindingVariableId: Int
        get() = BR.TVSeriesDetails
    override val bindingVariableValue: Any
        get() = TVSeriesDetailsFragmentArgs.fromBundle(requireArguments()).TVSeriesDetails

    override fun getViewModel(): HomeViewModel {
        TODO("Not yet implemented")
    }

}