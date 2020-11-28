package com.app.movie.presentation.ui.movies.toprated

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemMovieTopRatedBinding
import com.app.movie.datasource.network.models.movies.MovieTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MovieTopRatedAdapter(
    private val movieInteraction: MoviesInteraction
) :
    BasePagingDataAdapter<MovieTopRatedResult>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieTopRatedViewHolder(
            ItemMovieTopRatedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), movieInteraction
        )
    }

    inner class MovieTopRatedViewHolder(
        private val binding: ItemMovieTopRatedBinding,
        private val movieInteraction: MoviesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItem(position)
            binding.executePendingBindings()
            binding.detailsViewTopRated.actorsRecyclerViewTopRated.addOnItemTouchListener(object :
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

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface MoviesInteraction {
        fun onMovieItemSelected(
            position: Int,
            item: MovieTopRatedResult,
            binding: ItemMovieTopRatedBinding
        )
    }

}