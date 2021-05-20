package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Sery


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Sery){


    }

    companion object{
        fun create(parent: ViewGroup) : EpisodeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_episodes, parent, false)
            return EpisodeViewHolder(view)
        }
    }
}