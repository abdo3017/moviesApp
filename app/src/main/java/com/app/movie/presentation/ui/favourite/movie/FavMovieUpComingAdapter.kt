package com.app.movie.presentation.ui.favourite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemFavMovieUpComingBinding
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class FavMovieUpComingAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<MovieUpComingResult, ListItemFavMovieUpComingBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieViewHolder(
            ListItemFavMovieUpComingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MovieViewHolder(
        private val binding: ListItemFavMovieUpComingBinding,
        private val itemClickListener: ItemClickListener
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.isFav = true
            listView[position] = binding
            binding.position = position
            binding.listener = itemClickListener
            binding.movie = getItems(position)
            binding.executePendingBindings()
        }
    }

    override fun addFavItems(favouriteList: List<MovieUpComingResult>) {
        this.favouriteList = favouriteList
    }
}