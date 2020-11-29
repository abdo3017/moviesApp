package com.app.movie.presentation.ui.tv.toprated

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.databinding.ItemTVTopRatedBinding
import com.app.movie.datasource.network.models.tv.TVSeriesTopRatedResult
import com.app.movie.presentation.base.BasePagingDataAdapter
import com.app.movie.presentation.base.BaseViewHolder

class TVSeriesTopRatedAdapter(
    private val interaction: TVSeriesInteraction
) :
    BasePagingDataAdapter<TVSeriesTopRatedResult>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TVSerieTopRatedViewHolder(
            ItemTVTopRatedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), interaction
        )
    }

    inner class TVSerieTopRatedViewHolder(
        private val binding: ItemTVTopRatedBinding,
        private val tvSeriesInteraction: TVSeriesInteraction
    ) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            binding.tv = getItem(position)
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
                tvSeriesInteraction.itemSelected(
                    adapterPosition,
                    getItem(position)!!,
                    binding
                )
            }
        }

    }

    //We use interface to notify the activity with every selection like onItemSelected , onBookmarkSelected
    interface TVSeriesInteraction {
        fun itemSelected(
            position: Int,
            item: TVSeriesTopRatedResult,
            binding: ItemTVTopRatedBinding
        )
    }

}