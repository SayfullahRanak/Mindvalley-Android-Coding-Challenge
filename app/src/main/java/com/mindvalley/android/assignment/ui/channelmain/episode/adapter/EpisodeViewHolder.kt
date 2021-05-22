package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.LatestMedia
import com.mindvalley.android.assignment.utils.GlideApp
import kotlinx.android.synthetic.main.row_episodes.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: LatestMedia){
        GlideApp.with(itemView.context)
            .asBitmap()
            .load(content.coverAsset?.url)
            .placeholder(R.color.colorPrimaryDark)
            .into(itemView.coverIV)
        itemView.detailTV.text = content.title

    }

    companion object{
        fun create(parent: ViewGroup) : EpisodeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_episodes, parent, false)
            return EpisodeViewHolder(view)
        }
    }
}