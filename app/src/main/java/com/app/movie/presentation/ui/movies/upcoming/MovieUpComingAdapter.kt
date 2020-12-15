package com.app.movie.presentation.ui.movies.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemMovieUpComingBinding
import com.app.movie.datasource.network.models.movies.MovieUpComingResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class MovieUpComingAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<MovieUpComingResult, ItemMovieUpComingBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieUpComingViewHolder(
            ItemMovieUpComingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MovieUpComingViewHolder(
        private val binding: ItemMovieUpComingBinding,
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

    override fun addFavItems(favouriteList: List<MovieUpComingResult>) {
        this.favouriteList = favouriteList
    }
}