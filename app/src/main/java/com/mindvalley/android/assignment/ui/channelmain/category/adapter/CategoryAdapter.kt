package com.mindvalley.android.assignment.ui.channelmain.category.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Category


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class CategoryAdapter() :
        RecyclerView.Adapter<CategoryViewHolder>(){

    private var items: List<*>? = ArrayList<Category>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return when(viewType){
            R.layout.row_category -> CategoryViewHolder.create(
                    parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_category
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items?.get(position) as Category
        holder.bindTo(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun <T>updateList(list: List<T>){
        items = list
        notifyDataSetChanged()
    }

}