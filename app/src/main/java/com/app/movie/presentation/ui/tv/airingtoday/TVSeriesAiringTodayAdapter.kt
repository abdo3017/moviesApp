package com.app.movie.presentation.ui.tv.airingtoday

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemTVAiringTodayBinding
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class TVSeriesAiringTodayAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesAiringTodayResult, ItemTVAiringTodayBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesAiringTodayViewHolder(
            ItemTVAiringTodayBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class TVSeriesAiringTodayViewHolder(
        private val binding: ItemTVAiringTodayBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.isFav = favouriteList.any { it.id == getItem(position)!!.id }
            listView[position] = binding
            binding.position = position
            binding.listener = itemClickListener
            binding.tv = getItem(position)
            binding.executePendingBindings()
        }

    }

    override fun addFavItems(favouriteList: List<TVSeriesAiringTodayResult>) {
        this.favouriteList = favouriteList
    }


}