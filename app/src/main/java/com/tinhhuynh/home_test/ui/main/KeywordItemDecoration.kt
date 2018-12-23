package com.tinhhuynh.home_test.ui.main

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class KeywordItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
//        outRect.bottom = space

        // Add left margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = space
        } else {
            outRect.left = 0
        }
    }
}