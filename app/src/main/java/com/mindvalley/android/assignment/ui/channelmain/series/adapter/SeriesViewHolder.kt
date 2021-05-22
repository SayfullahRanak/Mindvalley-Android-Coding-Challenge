package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Sery
import kotlinx.android.synthetic.main.row_episodes.view.*
import kotlinx.android.synthetic.main.row_series.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Sery){
        Glide.with(itemView.context)
                .asBitmap()
                .load(content.coverAsset?.url)
                .placeholder(R.color.colorPrimaryDark)
                .into(itemView.rectangularIV)
        itemView.textView.text = content.title

    }

    companion object{
        fun create(parent: ViewGroup) : SeriesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_series, parent, false)
            return SeriesViewHolder(view)
        }
    }
}