package com.mindvalley.android.assignment.ui.channelmain.episode.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.LatestMedia
import com.mindvalley.android.assignment.entities.Sery
import com.mindvalley.android.assignment.ui.channelmain.channel.adapter.EpisodeViewHolder
import com.mindvalley.android.assignment.ui.channelmain.newepisode.adapter.NewEpisodeViewHolder

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class EpisodeAdapter :
        RecyclerView.Adapter<EpisodeViewHolder>(){

    private var items: List<*>? = ArrayList<LatestMedia>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return when(viewType){
            R.layout.row_episodes -> EpisodeViewHolder.create(
                    parent
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_episodes
    }


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = items?.get(position) as Sery
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