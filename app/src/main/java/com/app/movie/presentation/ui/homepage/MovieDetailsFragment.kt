package com.app.movie.presentation.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.movie.BR
import com.app.movie.R
import com.app.movie.databinding.FragmentMovieDetailsBinding
import com.app.movie.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return getMRootView()
    }

    override val layoutId: Int
        get() = R.layout.fragment_movie_details
    override val bindingVariableId: Int
        get() = BR.movieDetails
    override val bindingVariableValue: Any
        get() = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movieDetails

    override fun getViewModel(): HomeViewModel {
        TODO("Not yet implemented")
    }

}