package com.mindvalley.android.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mindvalley.android.assignment.entities.Category

@Dao
interface CategoriesModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    @Query("SELECT * FROM Category")
    suspend fun getAllCategory():  List<Category>

    @Query("DELETE FROM Category")
    suspend fun clearAllCategories()



}