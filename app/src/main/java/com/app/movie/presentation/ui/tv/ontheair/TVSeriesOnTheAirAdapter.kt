package com.app.movie.presentation.ui.tv.ontheair

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemTVOnTheAirBinding
import com.app.movie.datasource.network.models.tv.TVSeriesOnTheAirResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class TVSeriesOnTheAirAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesOnTheAirResult, ItemTVOnTheAirBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesOnTheAirViewHolder(
            ItemTVOnTheAirBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class TVSeriesOnTheAirViewHolder(
        private val binding: ItemTVOnTheAirBinding,
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

    override fun addFavItems(favouriteList: List<TVSeriesOnTheAirResult>) {
        this.favouriteList = favouriteList
    }

}