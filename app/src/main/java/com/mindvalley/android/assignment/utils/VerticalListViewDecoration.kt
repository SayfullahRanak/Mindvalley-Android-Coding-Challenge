package com.mindvalley.android.assignment.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.dpTopx
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.getScreenWidth
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.getSpcing


class VerticalListViewDecoration(private val context: Context, @DrawableRes dividerRes: Int) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable = ContextCompat.getDrawable(context, dividerRes)!!

    override fun onDrawOver(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight - (getScreenWidth(context).toFloat()/20).toInt()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = getScreenWidth(context).toFloat()/20
        outRect.top = spacing.toInt()

    }
}