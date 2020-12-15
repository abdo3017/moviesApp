package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemHorBinding
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class TVSeriesTopRatedAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesTopRatedResult, ListItemHorBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesViewHolder(
            ListItemHorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class TVSeriesViewHolder(
        private val binding: ListItemHorBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.isFav = favouriteList.any { it.id == getItem(position)!!.id }
            listView[position] = binding
            binding.position = position
            binding.listener = itemClickListener
            binding.tvSeries = getItems(position)
            binding.executePendingBindings()
        }
    }

    override fun addFavItems(favouriteList: List<TVSeriesTopRatedResult>) {
        this.favouriteList = favouriteList
    }
}