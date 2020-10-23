package com.app.movie.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.app.movie.R
import com.app.movie.databinding.ActivityMainBinding
import com.app.movie.databinding.TestBinding
import com.app.movie.domain.models.MovieNowPlaying
import com.app.movie.presentation.viewmodels.MovieNowPlayingViewModel
import com.example.movieapp.domain.state.DataState
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DrawerLayout.DrawerListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerHeaderBinding: TestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavigation()
        initViews()
//         val movieNowPlayingViewModel: MovieNowPlayingViewModel by viewModels()
//
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

    private fun initViews() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        val radius = resources.getDimension(R.dimen._16sdp)
        val navigationView: NavigationView = binding.navView
        val navViewBackground = navigationView.background as MaterialShapeDrawable
        navViewBackground.shapeAppearanceModel = navViewBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .build()
        binding.drawerLayout.addDrawerListener(this)
        drawerHeaderBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.test,
            binding.navView,
            false
        )
        binding.navView.addHeaderView(drawerHeaderBinding.root)
    }

    /**
     * Initialize Navigation Graph
     * */
    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.navigationBottom, navController)

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerStateChanged(newState: Int) {
    }
}