package com.app.movie.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.app.movie.R
import com.app.movie.databinding.FragmentBaseBinding
import kotlinx.android.synthetic.main.app_bar_layout.view.*

/**
 * [BaseFragment] class
 *
 * @author Abdelrahman Ghazi
 * @since 23 october 2020
 */
abstract class BaseFragment(
    var layout: Int,
    private var toolbarType: ToolbarType = ToolbarType.DEFAULT,
    private var showNavigation: Boolean,

    ) : Fragment() {
    private lateinit var binding: FragmentBaseBinding
    var toolbarName: MutableLiveData<String> = MutableLiveData()


    /**
     * check toolbar type and show related views based on that type
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        LayoutInflater.from(context).inflate(layout, binding.baseFrameLayout, true)
        val view = binding.baseFrameLayout.getChildAt(0)
        doOnCreate(view)
        observeToolbarName()
        initViews()
        return binding.root
    }

    /**
     * method that takes as parameter the inflated layout in FrameLayout
     * and pass it to child fragment
     */
    abstract fun doOnCreate(view: View)

    /**
     * observe toolbar changes that set by child fragment
     */
    private fun observeToolbarName() {
        toolbarName.observe(viewLifecycleOwner, { name ->
            binding.toolbar.toolbarTV.text = name
        })
    }

    private fun initViews() {
        when (toolbarType) {
            ToolbarType.DEFAULT -> {
                binding.showToolbar = true
            }
            ToolbarType.NONE -> {
                binding.showToolbar = false
            }
        }
        binding.toolbar.toolbarBackBtn.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                .popBackStack()
        }
    }
}

enum class ToolbarType {
    DEFAULT,
    NONE
}