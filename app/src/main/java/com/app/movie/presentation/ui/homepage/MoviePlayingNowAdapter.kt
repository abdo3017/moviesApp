package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemVerBinding
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MoviePlayingNowAdapter(items: MutableList<MovieNowPlayingResultsItem>) :
    BaseRecyclerViewAdapter<MovieNowPlayingResultsItem>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviePlayingNowViewHolder(
            ListItemVerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class MoviePlayingNowViewHolder(private val binding: ListItemVerBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItems()[position]
        }

    }
}