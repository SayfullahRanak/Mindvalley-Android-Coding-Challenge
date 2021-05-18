package com.mindvalley.android.assignment.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media(
    @PrimaryKey(autoGenerate = true)
    val channel: Channel,
    val coverAsset: CoverAsset,
    val title: String,
    val type: String
)