package com.tinhhuynh.home_test.data

import com.google.gson.annotations.SerializedName

data class Product(
        var badges: List<Badge> = emptyList(),
        @SerializedName("rating_average")
        var ratingAverage: Double = 0.0,
        var price: Long = 0,
        var name: String = "",
        @SerializedName("review_count")
        var reviewCount: Int = 0,
        var id: Long = 0,
        @SerializedName("thumbnail_url")
        var thumbnailUrl: String= "",
        @SerializedName("discount_rate")
        var discountRate: Int = 0
)

data class Badge(
	var code: String = "",
	var text: String? = null,
	var option: String? = null
)

