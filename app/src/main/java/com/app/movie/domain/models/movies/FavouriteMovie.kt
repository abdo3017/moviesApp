package com.app.movie.domain.models.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavouriteMovie(
    val id: Int? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null
) : Parcelable