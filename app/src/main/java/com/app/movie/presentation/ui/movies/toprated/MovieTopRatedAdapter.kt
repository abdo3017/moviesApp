package com.app.movie.presentation.ui.movies.toprated

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemMovieTopRatedBinding
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class MovieTopRatedAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<MovieTopRatedResult, ItemMovieTopRatedBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieTopRatedViewHolder(
            ItemMovieTopRatedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MovieTopRatedViewHolder(
        private val binding: ItemMovieTopRatedBinding,
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

    override fun addFavItems(favouriteList: List<MovieTopRatedResult>) {
        this.favouriteList = favouriteList
    }
}