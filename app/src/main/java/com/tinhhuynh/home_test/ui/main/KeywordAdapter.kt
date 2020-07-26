package com.tinhhuynh.home_test.ui.main

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinhhuynh.home_test.R
import com.tinhhuynh.home_test.ui.KeywordDisplayObject
import kotlinx.android.synthetic.main.item_keywords.view.*

class KeywordAdapter(private val context: Context)
    : androidx.recyclerview.widget.RecyclerView.Adapter<KeywordViewHolder>() {
    var keywords: List<KeywordDisplayObject> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = keywords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder =
            KeywordViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_keywords, parent, false))

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

}

class KeywordViewHolder(private val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val textKeyword = view.text_keyword

    fun bind(keywordDisplayObject: KeywordDisplayObject) {
        textKeyword.text = keywordDisplayObject.keyword
        view.cardView.setCardBackgroundColor(keywordDisplayObject.backgroundColor)
    }
}