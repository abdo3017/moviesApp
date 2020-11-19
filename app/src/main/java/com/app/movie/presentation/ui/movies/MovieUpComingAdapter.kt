package com.app.movie.presentation.ui.movies

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemMovieUpComingBinding
import com.app.movie.datasource.network.models.MovieUpComingResult
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MovieUpComingAdapter(
    items: MutableList<MovieUpComingResult>,
    private val movieInteraction: MoviesInteraction
) :
    BaseRecyclerViewAdapter<MovieUpComingResult>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieUpComingViewHolder(
            ItemMovieUpComingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), movieInteraction
        )
    }

    inner class MovieUpComingViewHolder(
        private val binding: ItemMovieUpComingBinding,
        private val movieInteraction: MoviesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItems()[position]
            binding.executePendingBindings()
            binding.detailsViewUpComing.actorsRecyclerViewUpComing.addOnItemTouchListener(object :
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
                movieInteraction.onMovieItemSelected(adapterPosition, getItems()[position], binding)
            }
        }

    }

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface MoviesInteraction {
        fun onMovieItemSelected(
            position: Int,
            item: MovieUpComingResult,
            binding: ItemMovieUpComingBinding
        )
    }

}