//package com.app.movie.presentation.base
//
//import androidx.paging.PagedListAdapter
//import androidx.recyclerview.widget.DiffUtil
//
//abstract class BasePagedListAdapter<T> : PagedListAdapter<T,BaseViewHolder>(MovieDiffCallback()) {
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        holder.onBind(position)
//    }
//
//    override fun getItemCount(): Int {
//        return if (currentList != null && currentList!!.size >  0) currentList!!.size else 1
//    }
//
//    class MovieDiffCallback : DiffUtil.ItemCallback<T>() {
//        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
//            return oldItem.id == newItem.id
//        }
//    }
//
//}