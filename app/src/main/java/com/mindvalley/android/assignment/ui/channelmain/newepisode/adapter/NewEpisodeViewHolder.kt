package com.mindvalley.android.assignment.ui.channelmain.newepisode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Media
import com.mindvalley.android.assignment.utils.GlideApp
import kotlinx.android.synthetic.main.row_episodes.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class NewEpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Media){
        GlideApp.with(itemView.context)
                .asBitmap()
                .load(content.coverAsset.url)
                .apply{RequestOptions.placeholderOf(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image)}
                .into(itemView.coverIV)
        itemView.detailTV.text = content.title
        itemView.subdetailTV.text = content.channel.title


    }

    companion object{
        fun create(parent: ViewGroup) : NewEpisodeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_episodes, parent, false)
            return NewEpisodeViewHolder(view)
        }
    }
}