package com.tinhhuynh.home_test.utils

import com.tinhhuynh.home_test.BadgeType
import com.tinhhuynh.home_test.data.Badge
import com.tinhhuynh.home_test.data.Product
import com.tinhhuynh.home_test.data.ProductDisplayObject
import java.text.NumberFormat
import java.util.*

class ProductHelper {
    fun convertToDisplayObject(product: Product): ProductDisplayObject {
        return ProductDisplayObject(product.id,
                product.ratingAverage,
                product.name,
                product.thumbnailUrl).apply {
            this.price = prettyPrice(product.price)
            this.discountRate = prettyDiscountRate(product.discountRate)
            this.reviewCount = prettyReviewCount(product.reviewCount)
            parseBadges(this, product.badges)
        }
    }

    private fun prettyReviewCount(reviewCount: Int): String {
        return if (reviewCount == 0) "" else "($reviewCount)"
    }


    private fun prettyPrice(price: Long): String {
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        return formatter.format(price)
    }


    private fun prettyDiscountRate(discountRate: Int): String {
        return if (discountRate == 0) "" else "-$discountRate%"
    }

    private fun parseBadges(productDisplayObject: ProductDisplayObject, badges: List<Badge>) {
        if (badges.isNullOrEmpty()) {
            return
        }
        badges.forEach {
            when (it.code) {
                BadgeType.TIKINOW.type -> productDisplayObject.isTikiNow = true
                BadgeType.FAST_DELIVERY.type -> {
                    productDisplayObject.isFastDelivery = true
                    productDisplayObject.fastDeliveryText = "${it.text} ${it.option}"
                }
                BadgeType.OPTION_COLOR.type -> productDisplayObject.isColorOption = true
            }
        }
    }

}