package com.app.movie.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.app.movie.R
import com.app.movie.databinding.ActivityMainBinding
import com.app.movie.databinding.TestBinding
import com.app.movie.presentation.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), DrawerLayout.DrawerListener {
    private lateinit var drawerHeaderBinding: TestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()
        initViews()
    }

    private fun initViews() {
        getViewDataBinding().drawerLayout.closeDrawer(GravityCompat.START)
        val radius = resources.getDimension(R.dimen._16sdp)
        val navigationView: NavigationView = getViewDataBinding().navView
        val navViewBackground = navigationView.background as MaterialShapeDrawable
        navViewBackground.shapeAppearanceModel = navViewBackground.shapeAppearanceModel
            .toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)
            .build()
        getViewDataBinding().drawerLayout.addDrawerListener(this)
        drawerHeaderBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.test,
            getViewDataBinding().navView,
            false
        )
        getViewDataBinding().navView.addHeaderView(drawerHeaderBinding.root)
    }

    /**
     * Initialize Navigation Graph
     * */
    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(getViewDataBinding().navigationBottom, navController)

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}