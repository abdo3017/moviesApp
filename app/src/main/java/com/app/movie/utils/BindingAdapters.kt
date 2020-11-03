package com.app.movie.utils

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.movie.presentation.base.BaseRecyclerViewAdapter
import com.bumptech.glide.Glide

@Suppress("UNCHECKED_CAST")
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:ratingBar")
    fun setRatingBarView(movieRate: RatingBar, movieVoteAverage: Double?) {
        val rating = (movieVoteAverage!! * 5 / 9).toFloat()
        movieRate.numStars = 5
        movieRate.stepSize = 0.1f
        movieRate.rating = rating
        movieRate.setIsIndicator(true)
    }

    @JvmStatic
    @BindingAdapter("android:trailerImage")
    fun setMovieTrailerImage(movieTrailerThumbnail: ImageView, trailerKey: String?) {
        Glide.with(movieTrailerThumbnail.context)
            .load(Constants.YOUTUBE_VIDEO_THUMBNAIL + trailerKey + "/0.jpg")
            .into(movieTrailerThumbnail)
    }

    @JvmStatic
    @BindingAdapter("android:recyclerAdapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: MutableList<T>?) {
        items?.let { (recyclerView.adapter as BaseRecyclerViewAdapter<T>?)?.addItems(it) }
    }


}