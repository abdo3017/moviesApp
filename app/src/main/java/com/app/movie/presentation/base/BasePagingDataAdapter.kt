package com.app.movie.presentation.base

import android.annotation.SuppressLint
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil


abstract class BasePagingDataAdapter<T : Any, R : Any> :
    PagingDataAdapter<T, BaseViewHolder>(MovieDiffCallback<T>()) {
    var listView: HashMap<Int, R> = HashMap()
    var list: List<T> = emptyList()
    var favouriteList: List<T> = emptyList()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    abstract fun addFavItems(favouriteList: List<T>)

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
