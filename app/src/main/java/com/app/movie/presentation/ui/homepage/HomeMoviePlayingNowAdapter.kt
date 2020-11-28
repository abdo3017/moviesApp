package com.app.movie.presentation.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.movie.databinding.ListItemVerBinding
import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class HomeMoviePlayingNowAdapter(
    private val movieInteraction: MovieInteraction
) :
    BasePagingDataAdapter<MovieNowPlayingResultsItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface MovieInteraction {
        fun onMovieItemSelected(item: MovieNowPlayingResultsItem)
    }


//    interface MoviePlayingNowInteraction {
//        fun onItemSelected(item: MovieNowPlayingResultsItem)
//    }
}