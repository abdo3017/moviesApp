package com.app.movie.presentation.ui.favourite.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemFavTVPopularBinding
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class FavTVPopularAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<TVSeriesPopularResult, ListItemFavTVPopularBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(
            ListItemFavTVPopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MovieViewHolder(
        private val binding: ListItemFavTVPopularBinding,
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

    override fun addFavItems(favouriteList: List<TVSeriesPopularResult>) {
        this.favouriteList = favouriteList
    }
}