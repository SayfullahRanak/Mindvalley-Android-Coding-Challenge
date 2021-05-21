package com.mindvalley.android.assignment.ui.channelmain.channel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.mindvalley.android.assignment.R
import com.mindvalley.android.assignment.entities.Channel
import com.mindvalley.android.assignment.entities.LatestMedia
import com.mindvalley.android.assignment.ui.channelmain.episode.adapter.EpisodeAdapter
import com.mindvalley.android.assignment.ui.channelmain.series.adapter.SeriesAdapter
import com.mindvalley.android.assignment.utils.GlideApp
import com.mindvalley.android.assignment.utils.HorizontalListViewDecoration
import kotlinx.android.synthetic.main.activity_channel_list.*
import kotlinx.android.synthetic.main.row_channel.view.*
import kotlinx.android.synthetic.main.row_episodes.view.*


/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class ChannelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(content: Channel){

        GlideApp.with(itemView.context)
            .asBitmap()
            .load(content.iconAsset)
            .apply{ RequestOptions.placeholderOf(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image)}
            .into(itemView.titleIV)
        itemView.titleTV.text = content.title

        if(content.series.isEmpty()){
            itemView.subtitleTV.text = "${content.latestMedia.size} episodes"
            val episodeAdapter  = EpisodeAdapter(content.latestMedia)
            itemView.channelentityLV.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//            itemView.channelentityLV.addItemDecoration(HorizontalListViewDecoration())
            itemView.channelentityLV.adapter = episodeAdapter
        }else{
            itemView.subtitleTV.text = "${content.series.size} series"
            val episodeAdapter  = SeriesAdapter(content.series)
            itemView.channelentityLV.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//            itemView.channelentityLV.addItemDecoration(HorizontalListViewDecoration())
            itemView.channelentityLV.adapter = episodeAdapter
        }
    }

    companion object{
        fun create(parent: ViewGroup) : ChannelViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.row_channel, parent, false)
            return ChannelViewHolder(view)
        }
    }
}