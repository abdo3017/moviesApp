package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemHorBinding
import com.app.movie.datasource.network.models.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.app.movie.presentation.base.BaseViewHolder

class TVSeriesTopRatedAdapter(
    items: MutableList<TVSeriesTopRatedResult>,
    private val tvSeriesInteraction: TVSeriesInteraction
) :
    BaseRecyclerViewAdapter<TVSeriesTopRatedResult>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesViewHolder(
            ListItemHorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), tvSeriesInteraction
        )
    }

    inner class TVSeriesViewHolder(
        private val binding: ListItemHorBinding,
        private val tvSeriesInteraction: TVSeriesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.tvSeries = getItems()[position]
            binding.tvInteraction = tvSeriesInteraction
            binding.executePendingBindings()

        }

    }

    interface TVSeriesInteraction {
        fun onTVSeriesItemSelected(item: TVSeriesTopRatedResult)
    }
}