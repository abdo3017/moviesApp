package com.app.movie.datasource.network.models

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("product_id")
    val productId: Int? = null,

    @field:SerializedName("display")
    val display: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("image_2")
    val image2: String? = null,

    @field:SerializedName("discounted_price")
    val discountedPrice: String? = null
)
