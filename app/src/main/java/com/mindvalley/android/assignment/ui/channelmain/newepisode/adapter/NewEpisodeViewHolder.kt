package com.mindvalley.android.assignment.ui.channelmain.newepisode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Media
import com.mindvalley.android.assignment.utils.GlideApp
import kotlinx.android.synthetic.main.row_episodes.view.coverIV
import kotlinx.android.synthetic.main.row_episodes.view.detailTV
import kotlinx.android.synthetic.main.row_new_episodes.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class NewEpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Media){
        GlideApp.with(itemView.context)
                .asBitmap()
                .load(content.coverAsset?.url)
                .placeholder(R.color.colorPrimaryDark)
                .into(itemView.coverIV)
        itemView.detailTV.text = content.title
        itemView.subdetailTV.text = content.channel?.title


    }

    companion object{
        fun create(parent: ViewGroup) : NewEpisodeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_new_episodes, parent, false)
            return NewEpisodeViewHolder(view)
        }
    }
}