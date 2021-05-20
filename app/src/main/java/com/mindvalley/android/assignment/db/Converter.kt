package com.mindvalley.android.assignment.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.android.assignment.entities.*

class Converter {


    @TypeConverter
    fun fromCategoryList(categoryList : List<Category>) : String{
        return Gson().toJson(categoryList)
    }

    @TypeConverter
    fun toCategoryList(list: String ) : List<Category>{
        return Gson().fromJson(list,Array<Category>::class.java).asList()
    }

    @TypeConverter
    fun fromChannelList(channelList : List<Channel>) : String{
        return Gson().toJson(channelList)
    }

    @TypeConverter
    fun toChannelList(list: String ) : List<Channel>{
        return Gson().fromJson(list,Array<Channel>::class.java).asList()
    }

    @TypeConverter
    fun fromNewEpisodeList(channelList : List<Media>) : String{
        return Gson().toJson(channelList)
    }

    @TypeConverter
    fun toNewEpisodeList(list: String ) : List<Media>{
        return Gson().fromJson(list,Array<Media>::class.java).asList()
    }

    @TypeConverter
    fun fromCoverAsset(coverAsset: CoverAsset) : String{
        return Gson().toJson(coverAsset)
    }

    @TypeConverter
    fun toCoverAsset(item: String ) : CoverAsset{
        return Gson().fromJson(item,CoverAsset::class.java)
    }

    @TypeConverter
    fun fromIconAsset(iconAsset: IconAsset?) : String{

        iconAsset?.let {
            return Gson().toJson(iconAsset)
        } ?: return ""
    }

    @TypeConverter
    fun toChannelMedia(item: String ) : ChannelMedia{
        return Gson().fromJson(item,ChannelMedia::class.java)
    }

    @TypeConverter
    fun fromChannelMedia(channelMedia: ChannelMedia) : String{
        return Gson().toJson(channelMedia)
    }

    @TypeConverter
    fun toIconAsset(item: String ) : IconAsset{
        Gson().fromJson(item,IconAsset::class.java)?.let {
            return it
        } ?: return IconAsset("")
    }

    @TypeConverter
    fun fromLatestMediaList(latestMedia: List<LatestMedia>) : String{
        return Gson().toJson(latestMedia)
    }

    @TypeConverter
    fun toLatestMediaList(list: String ) : List<LatestMedia>{
        return Gson().fromJson(list,Array<LatestMedia>::class.java).asList()
    }

    @TypeConverter
    fun fromSeriesList(series: List<Sery>) : String{
        return Gson().toJson(series)
    }

    @TypeConverter
    fun toSeriesList(list: String ) : List<Sery>{
        return Gson().fromJson(list,Array<Sery>::class.java).asList()
    }




}