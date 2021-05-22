package com.mindvalley.android.assignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mindvalley.android.assignment.entities.Category
import com.mindvalley.android.assignment.entities.Channel
import com.mindvalley.android.assignment.entities.Media
import com.mindvalley.android.assignment.utils.Cons.Companion.ROOM_VERSION

@Database(version = ROOM_VERSION, entities = [ Channel::class,Media::class, Category::class], exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getChannelDao() : ChannelsModelDao
    abstract fun getNewEpisodeDao() : NewEpisodeModelDao
    abstract fun getCategoryDao() : CategoriesModelDao


    companion object{
        val CHANNEL_DB = "channel.db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, CHANNEL_DB)
                .fallbackToDestructiveMigration()
                .build()
        }
}