package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Channel

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class ChannelAdapter :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    private var items: List<*>? = ArrayList<Channel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.row_channel -> ChannelViewHolder.create(
                    parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_channel
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items?.get(position) as Channel
        (holder as ChannelViewHolder).bindTo(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun <T>updateList(list: List<T>){
        items = list
        notifyDataSetChanged()
    }

}