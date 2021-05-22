package com.mindvalley.android.assignment.utils

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.dpTopx
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.getScreenWidth
import com.mindvalley.android.assignment.utils.ProjectUtils.Companion.getSpcing

class HorizontalListViewDecoration(private val context : Context) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = getScreenWidth(context).toFloat()/30
        outRect.right = (getScreenWidth(context).toFloat()/30).toInt()
        outRect.top = (getScreenWidth(context).toFloat()/40).toInt()
        outRect.bottom = (getScreenWidth(context).toFloat()/40).toInt()
    }
}