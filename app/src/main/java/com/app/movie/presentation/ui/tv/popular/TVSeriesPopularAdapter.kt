package com.app.movie.presentation.ui.tv.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemTVPopularBinding
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class TVSeriesPopularAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesPopularResult, ItemTVPopularBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesPopularViewHolder(
            ItemTVPopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class TVSeriesPopularViewHolder(
        private val binding: ItemTVPopularBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            listView[position] = binding
            binding.isFav = favouriteList.any { it.id == getItem(position)!!.id }
            binding.position = position
            binding.listener = itemClickListener
            binding.tv = getItem(position)
            binding.executePendingBindings()
        }
    }

    override fun addFavItems(favouriteList: List<TVSeriesPopularResult>) {
        this.favouriteList = favouriteList
    }

}