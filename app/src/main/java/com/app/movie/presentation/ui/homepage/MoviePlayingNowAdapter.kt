package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemVerBinding
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class MoviePlayingNowAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<MovieNowPlayingResultsItem, ListItemVerBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviePlayingNowViewHolder(
            ListItemVerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    override fun addFavItems(favouriteList: List<MovieNowPlayingResultsItem>) {
        this.favouriteList = favouriteList
    }

    inner class MoviePlayingNowViewHolder(
        private val binding: ListItemVerBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.isFav = favouriteList.any { it.id == getItem(position)!!.id }
            listView[position] = binding
            binding.position = position
            binding.listener = itemClickListener
            binding.movie = getItem(position)
            binding.executePendingBindings()
        }
    }
}

