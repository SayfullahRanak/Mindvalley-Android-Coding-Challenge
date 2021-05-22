package com.mindvalley.android.assignment.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name: String?
)