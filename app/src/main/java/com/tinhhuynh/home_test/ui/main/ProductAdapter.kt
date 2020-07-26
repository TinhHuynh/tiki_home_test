package com.tinhhuynh.home_test.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tinhhuynh.home_test.R
import com.tinhhuynh.home_test.data.ProductDisplayObject
import kotlinx.android.synthetic.main.item_product.view.*


class ProductAdapter(private val context: Context)
    : RecyclerView.Adapter<KeywordViewHolder>() {
    var product: List<ProductDisplayObject> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = product.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder =
            KeywordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_product, parent, false))

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(product[position])
    }

}

class KeywordViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(product: ProductDisplayObject) {
        bindThumbnail(product.thumbnailUrl)
        bindTikiNow(product.isTikiNow)
        bindName(product.name)
        bindPrice(product.price)
        bindDiscountRate(product.discountRate)
        bindFastDelivery(product)
        bindColorOption(product.isColorOption)
        bindReview(product)
    }



    private fun bindThumbnail(thumbnailUrl: String) {
        Glide.with(view.context)
                .load(thumbnailUrl)
                .placeholder(R.drawable.img_placeholder)
                .into(view.img_thumbnail)
    }

    private fun bindTikiNow(tikiNow: Boolean) {
        view.img_tiki_now.visibility = if (tikiNow) VISIBLE else GONE
    }
    private fun bindName(name: String) {
        view.txt_name.text = name
    }

    private fun bindPrice(price: String) {
        view.txt_price.text = price
    }

    private fun bindDiscountRate(discountRate: String) {
        view.txt_discount_rate.text = discountRate
    }

    private fun bindFastDelivery(product: ProductDisplayObject) {
        if (product.isFastDelivery) {
            view.txt_fast_delivery.visibility = VISIBLE
            view.txt_fast_delivery.text = product.fastDeliveryText
        } else {
            view.txt_fast_delivery.visibility = INVISIBLE
        }
    }

    private fun bindColorOption(colorOption: Boolean) {
        view.txt_color_option.visibility = if (colorOption) VISIBLE else INVISIBLE
    }


    private fun bindReview(product: ProductDisplayObject) {
        if (product.ratingAverage == 0.0) {
            view.group_review.visibility = INVISIBLE
        } else {
            view.group_review.visibility = VISIBLE
            view.rating_bar.rating = product.ratingAverage.toFloat()
            view.txt_review_count.text = product.reviewCount
        }
    }
}