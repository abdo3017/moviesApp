package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ItemTrailerBinding
import com.app.movie.datasource.network.models.MovieVideosResultsItem
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MoviesTrailerAdapter(items: MutableList<MovieVideosResultsItem>) :
    BaseRecyclerViewAdapter<MovieVideosResultsItem>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviesTrailerViewHolder(
            ItemTrailerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class MoviesTrailerViewHolder(private val binding: ItemTrailerBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movieVideoTrailer = getItems()[position].key
        }

    }
}