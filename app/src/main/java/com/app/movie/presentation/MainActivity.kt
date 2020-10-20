package com.app.movie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.movie.R
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.presentation.viewmodels.MovieNowPlayingViewModel
import com.example.movieapp.domain.state.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val  movieNowPlayingViewModel : MovieNowPlayingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        movieNowPlayingViewModel.getMoviesNowPlaying()
//        movieNowPlayingViewModel.dataState.observe(this, Observer {
//            when(it){
//                is DataState.Loading -> {
//                    Log.d("teeeeeeeet","loading")
//                }
//                is DataState.Success<MovieNowPlaying> -> {
//                    Log.d("teeeeeeeet",it.data.toString())
//                }
//                is DataState.Error -> {
//                    Log.d("teeeeeeeet",  it.exception.message.toString())
//                }
//
//            }
//        })
    }
}