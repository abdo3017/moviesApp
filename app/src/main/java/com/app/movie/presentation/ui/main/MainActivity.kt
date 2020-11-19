package com.app.movie.presentation.ui.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.app.movie.R
import com.app.movie.databinding.ActivityMainBinding
import com.app.movie.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()
        initViews()
    }

    private fun initViews() {
    }

    /**
     * Initialize Navigation Graph
     * */
    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, navController)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}