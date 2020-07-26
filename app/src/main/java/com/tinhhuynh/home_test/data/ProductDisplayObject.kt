package com.tinhhuynh.home_test.data

data class ProductDisplayObject(
        var id: Long = 0,
        var ratingAverage: Double = 0.0,
        var name: String = "",
        var thumbnailUrl: String= ""

){
    var price: String = ""
    var discountRate: String = ""
    var reviewCount: String = ""
    var isColorOption: Boolean = false
    var isFastDelivery: Boolean = false
    var fastDeliveryText: String = ""
    var isTikiNow: Boolean = false

    override fun toString(): String {
        return "ProductDisplayObject(id=$id, ratingAverage=$ratingAverage, name='$name', " +
                "thumbnailUrl='$thumbnailUrl', price='$price', discountRate='$discountRate'," +
                " reviewCount='$reviewCount', isColorOption=$isColorOption, " +
                "isFastDelivery=$isFastDelivery, fastDeliveryText='$fastDeliveryText'," +
                " isTikiNow=$isTikiNow)"
    }


}



