package com.mindvalley.android.assignment.ui.newepisode.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Media

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class NewEpisodeAdapter :
        RecyclerView.Adapter<NewEpisodeViewHolder>(){

    private var items: List<*>? = ArrayList<Media>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewEpisodeViewHolder {
        return when(viewType){
            R.layout.row_episodes -> NewEpisodeViewHolder.create(
                parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_episodes
    }


    override fun onBindViewHolder(holder: NewEpisodeViewHolder, position: Int) {
        val item = items?.get(position) as Media
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