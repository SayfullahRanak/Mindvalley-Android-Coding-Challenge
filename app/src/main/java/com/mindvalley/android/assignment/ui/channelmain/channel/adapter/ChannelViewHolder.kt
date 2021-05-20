package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Channel
import com.mindvalley.android.assignment.entities.LatestMedia


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class ChannelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Channel){


    }

    companion object{
        fun create(parent: ViewGroup) : EpisodeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_episodes, parent, false)
            return EpisodeViewHolder(view)
        }
    }
}