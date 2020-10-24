package com.app.movie.presentation.ui

import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.movie.R
import com.app.movie.databinding.FragmentSplashBinding
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.presentation.viewmodels.MovieNowPlayingViewModel
import com.example.movieapp.domain.state.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment(
    R.layout.fragment_splash,
    ToolbarType.NONE,
    false
) {
    lateinit var binding: FragmentSplashBinding
    private val movieNowPlayingViewModel: MovieNowPlayingViewModel by viewModels()

    override fun doOnCreate(view: View) {
        binding = DataBindingUtil.bind(view)!!
        movieNowPlayingViewModel.getMoviesNowPlaying()
        movieNowPlayingViewModel.dataState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {
                    // Log.d("teeeeeeeet", "loading")
                }
                is DataState.Success<MovieNowPlaying> -> {
                    // Log.d("teeeeeeeet", it.data.toString())
                }
                is DataState.Error -> {
                    // Log.d("teeeeeeeet", it.exception.message.toString())
                }

            }
        })

    }

}