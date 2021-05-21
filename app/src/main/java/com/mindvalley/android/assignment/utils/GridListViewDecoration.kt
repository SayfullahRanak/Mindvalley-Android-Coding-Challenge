package com.mindvalley.android.assignment.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridListViewDecoration : RecyclerView.ItemDecoration() {

    val margin16Px = 16

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.right = margin16Px
        outRect.left = margin16Px
        outRect.top = margin16Px
        outRect.bottom = margin16Px

    }
}