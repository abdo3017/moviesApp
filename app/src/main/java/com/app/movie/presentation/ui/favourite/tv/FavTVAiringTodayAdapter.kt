package com.app.movie.presentation.ui.favourite.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemFavTVAiringTodayBinding
import com.app.movie.datasource.network.models.tv.TVSeriesAiringTodayResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class FavTVAiringTodayAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesAiringTodayResult, ListItemFavTVAiringTodayBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(
            ListItemFavTVAiringTodayBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MovieViewHolder(
        private val binding: ListItemFavTVAiringTodayBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.isFav = true
            listView[position] = binding
            binding.position = position
            binding.listener = itemClickListener
            binding.tv = getItems(position)
            binding.executePendingBindings()
        }
    }

    override fun addFavItems(favouriteList: List<TVSeriesAiringTodayResult>) {
        this.favouriteList = favouriteList
    }
}