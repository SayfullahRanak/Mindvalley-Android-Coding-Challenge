package com.mindvalley.android.assignment.ui.channelmain.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Category
import kotlinx.android.synthetic.main.row_category.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Category){
        itemView.nameTV.text = content.name

    }

    companion object{
        fun create(parent: ViewGroup) : CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_category, parent, false)
            return CategoryViewHolder(view)
        }
    }
}