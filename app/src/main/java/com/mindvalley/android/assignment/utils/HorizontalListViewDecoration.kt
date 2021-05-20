package com.mindvalley.android.assignment.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalListViewDecoration : RecyclerView.ItemDecoration() {

        val margin16Px = 50
//            MyApplication.appInstance.resources.getDimension(R.dimen.padding16px).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position  = parent.getChildAdapterPosition(view)

        outRect.right = margin16Px
//        outRect.left = margin16Px
//        outRect.top = margin16Px
//        outRect.bottom = margin16Px

    }
}