package com.app.movie.presentation.ui.tv.popular

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemTVPopularBinding
import com.app.movie.datasource.network.models.tv.TVSeriesPopularResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class TVSeriesPopularAdapter(
    private val tvSeriesInteraction: TVSeriesInteraction
) :
    BasePagingDataAdapter<TVSeriesPopularResult>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSeriesPopularViewHolder(
            ItemTVPopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), tvSeriesInteraction
        )
    }

    inner class TVSeriesPopularViewHolder(
        private val binding: ItemTVPopularBinding,
        private val tvSeriesInteraction1: TVSeriesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.tv = getItem(position)
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
                tvSeriesInteraction1.onItemSelected(adapterPosition, getItem(position)!!, binding)
            }
        }

    }

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface TVSeriesInteraction {
        fun onItemSelected(
            position: Int,
            item: TVSeriesPopularResult,
            binding: ItemTVPopularBinding
        )
    }

}