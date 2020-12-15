package com.app.movie.presentation.ui.movies.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemMoviePopularBinding
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder
import com.app.movie.presentation.base.ItemClickListener

class MoviePopularAdapter(
    private val itemClickListener: ItemClickListener
) :
    BasePagingDataAdapter<MoviePopularResult, ItemMoviePopularBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviePopularViewHolder(
            ItemMoviePopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), itemClickListener
        )
    }

    inner class MoviePopularViewHolder(
        private val binding: ItemMoviePopularBinding,
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

    override fun addFavItems(favouriteList: List<MoviePopularResult>) {
        this.favouriteList = favouriteList
    }

}