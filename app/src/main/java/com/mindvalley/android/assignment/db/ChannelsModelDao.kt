package com.mindvalley.android.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mindvalley.android.assignment.entities.Channel

@Dao
interface ChannelsModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(channels: List<Channel>)

    @Query("SELECT * FROM Channel")
    suspend fun getAllChannel(): List<Channel>

    @Query("DELETE FROM Channel")
    suspend fun clearAllChannel()



}