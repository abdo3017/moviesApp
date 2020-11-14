package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemHorBinding
import com.app.movie.datasource.network.models.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.app.movie.presentation.base.BaseViewHolder

class TVSeriesTopRatedAdapter(items: MutableList<TVSeriesTopRatedResult>) :
    BaseRecyclerViewAdapter<TVSeriesTopRatedResult>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesViewHolder(
            ListItemHorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class TVSeriesViewHolder(private val binding: ListItemHorBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.tvSeries = getItems()[position]
        }

    }
}