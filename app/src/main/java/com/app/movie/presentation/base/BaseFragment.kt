package com.app.movie.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class BaseFragment<T : ViewDataBinding, V> : Fragment() {

    private lateinit var viewDataBinding: T
    private lateinit var navController: NavController
    private lateinit var mRootView: View
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding.root
        return mRootView
    }

    abstract val layoutId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        sitViewDataBindingVariable(bindingVariableId, bindingVariableValue)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

    abstract val bindingVariableId: Int
    abstract val bindingVariableValue: Any

    fun sitViewDataBindingVariable(bindingVariableId: Int, bindingVariableValue: Any) {
        viewDataBinding.setVariable(bindingVariableId, bindingVariableValue)
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    fun getNavController(): NavController {
        return navController
    }

    abstract fun getViewModel(): V

    fun getMRootView(): View {
        return mRootView
    }

    fun getMContext(): Context {
        return mContext
    }
}