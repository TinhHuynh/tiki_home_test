package com.tinhhuynh.home_test.ui.main

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinhhuynh.home_test.R
import com.tinhhuynh.home_test.utils.ColorUtils
import com.tinhhuynh.home_test.utils.KeywordUtils
import kotlinx.android.synthetic.main.item_keywords.view.*

class KeywordAdapter(val keywords: ArrayList<String>, val context: Context)
    : RecyclerView.Adapter<KeywordViewHolder>() {

    override fun getItemCount(): Int = keywords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder = KeywordViewHolder(context, LayoutInflater.from(context).inflate(R.layout.item_keywords, parent, false))

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

}

class KeywordViewHolder(val context: Context, val view: View) : RecyclerView.ViewHolder(view) {
    private val textKeyword = view.text_keyword

    fun bind(keyword: String) {
        val newKeyword = KeywordUtils.insertNewlineInMiddle(keyword)
        textKeyword.text = newKeyword
        val color = ColorUtils.randomMatColor(context, "700")
        if (view is CardView) {
            view.setCardBackgroundColor(color)
        } else {
            view.setBackgroundColor(color)
        }
    }
}