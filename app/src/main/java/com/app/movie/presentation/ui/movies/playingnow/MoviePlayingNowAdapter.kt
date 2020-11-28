package com.app.movie.presentation.ui.movies.playingnow

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemMoviePlayingNowBinding

import com.app.movie.datasource.network.models.movies.MovieNowPlayingResultsItem
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MoviePlayingNowAdapter(
    private val movieInteraction: MoviesInteraction
) :
    BasePagingDataAdapter<MovieNowPlayingResultsItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviePlayingNowViewHolder(
            ItemMoviePlayingNowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), movieInteraction
        )
    }

    inner class MoviePlayingNowViewHolder(
        private val binding: ItemMoviePlayingNowBinding,
        private val movieInteraction: MoviesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItem(position)
            binding.executePendingBindings()
            binding.detailsViewPlayingNow.actorsRecyclerViewPlayingNow.addOnItemTouchListener(object :
                RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
            //Notify the listener on movie item click
            itemView.setOnClickListener {
                movieInteraction.onMovieItemSelected(adapterPosition, getItem(position)!!, binding)
            }
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
    interface MoviesInteraction {
        fun onMovieItemSelected(
            position: Int,
            item: MovieNowPlayingResultsItem,
            binding: ItemMoviePlayingNowBinding
        )
    }

}