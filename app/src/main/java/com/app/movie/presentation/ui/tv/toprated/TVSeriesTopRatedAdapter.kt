package com.app.movie.presentation.ui.tv.toprated

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemTVTopRatedBinding
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class TVSeriesTopRatedAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesTopRatedResult, ItemTVTopRatedBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSerieTopRatedViewHolder(
            ItemTVTopRatedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class TVSerieTopRatedViewHolder(
        private val binding: ItemTVTopRatedBinding,
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

    override fun addFavItems(favouriteList: List<TVSeriesTopRatedResult>) {
        this.favouriteList = favouriteList
    }

}