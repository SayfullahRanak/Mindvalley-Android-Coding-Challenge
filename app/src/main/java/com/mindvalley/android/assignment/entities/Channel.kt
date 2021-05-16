package com.mindvalley.android.assignment.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val coverAsset: CoverAsset,
    val iconAsset: IconAsset,
    val latestMedia: List<LatestMedia>,
    val mediaCount: Int,
    val series: List<Sery>,
    val title: String
)