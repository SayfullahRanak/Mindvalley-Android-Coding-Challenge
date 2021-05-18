package com.mindvalley.android.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mindvalley.android.assignment.entities.Media

@Dao
interface NewEpisodeModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medias: List<Media>)

    @Query("SELECT * FROM Media")
    fun getAllCatModel(): List<Media>

    @Query("DELETE FROM Media")
    suspend fun clearAllCategories()



}