package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemHorBinding
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class TVSeriesTopRatedAdapter(
    private val tvSeriesInteraction: TVSeriesInteraction
) :
    BasePagingDataAdapter<TVSeriesTopRatedResult>() {
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
            binding.tvSeries = getItems(position)
            binding.tvInteraction = tvSeriesInteraction
            binding.executePendingBindings()

        }

    }

    interface TVSeriesInteraction {
        fun onTVSeriesItemSelected(item: TVSeriesTopRatedResult)
    }
}