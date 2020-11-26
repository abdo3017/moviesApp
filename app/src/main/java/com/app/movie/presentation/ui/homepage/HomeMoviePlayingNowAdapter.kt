package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.app.movie.databinding.ListItemVerBinding
import com.app.movie.datasource.network.models.MovieNowPlayingResultsItem
import com.app.movie.presentation.base.BaseViewHolder

class HomeMoviePlayingNowAdapter(
    private val movieInteraction: MovieInteraction
) :
    PagingDataAdapter<MovieNowPlayingResultsItem, HomeMoviePlayingNowAdapter.MoviePlayingNowViewHolder>(
        DataDifferentiate
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePlayingNowViewHolder {
        return MoviePlayingNowViewHolder(
            ListItemVerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), movieInteraction
        )
    }

    inner class MoviePlayingNowViewHolder(
        private val binding: ListItemVerBinding,
        private val movieInteraction: MovieInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItem(position)
            binding.movieInteraction = movieInteraction
            binding.executePendingBindings()

        }

    }

    object DataDifferentiate : DiffUtil.ItemCallback<MovieNowPlayingResultsItem>() {
        override fun areItemsTheSame(
            oldItem: MovieNowPlayingResultsItem,
            newItem: MovieNowPlayingResultsItem
        ): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieNowPlayingResultsItem,
            newItem: MovieNowPlayingResultsItem
        ): Boolean {
            return newItem == oldItem
        }

    }

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface MovieInteraction {
        fun onMovieItemSelected(item: MovieNowPlayingResultsItem)
    }

    override fun onBindViewHolder(holder: MoviePlayingNowViewHolder, position: Int) {
        holder.onBind(position)
    }
//    interface MoviePlayingNowInteraction {
//        fun onItemSelected(item: MovieNowPlayingResultsItem)
//    }
}