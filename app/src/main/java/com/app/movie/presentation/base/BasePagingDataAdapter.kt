package com.app.movie.presentation.base

import android.annotation.SuppressLint
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil


abstract class BasePagingDataAdapter<T : Any> :
    PagingDataAdapter<T, BaseViewHolder>(MovieDiffCallback<T>()) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun getItems(position: Int): T {
        return getItem(position)!!
    }
}

@SuppressLint("DiffUtilEquals")
class MovieDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    private val <T> T.id: Any
        get() = Any()

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
