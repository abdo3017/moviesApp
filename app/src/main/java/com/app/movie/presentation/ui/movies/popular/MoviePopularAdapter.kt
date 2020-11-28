package com.app.movie.presentation.ui.movies.popular

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemMoviePopularBinding
import com.app.movie.datasource.network.models.movies.MoviePopularResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class MoviePopularAdapter(
    private val movieInteraction: MoviesInteraction
) :
    BasePagingDataAdapter<MoviePopularResult>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MoviePopularViewHolder(
            ItemMoviePopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), movieInteraction
        )
    }

    inner class MoviePopularViewHolder(
        private val binding: ItemMoviePopularBinding,
        private val movieInteraction: MoviesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.movie = getItem(position)
            binding.executePendingBindings()
            binding.detailsViewPopular.actorsRecyclerViewPopular.addOnItemTouchListener(object :
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
            item: MoviePopularResult,
            binding: ItemMoviePopularBinding
        )
    }

}