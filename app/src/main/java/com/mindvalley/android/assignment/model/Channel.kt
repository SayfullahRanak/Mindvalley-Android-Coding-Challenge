package com.mindvalley.android.assignment.model

data class Channel(
    val coverAsset: CoverAsset,
    val iconAsset: IconAsset,
    val latestMedia: List<LatestMedia>,
    val mediaCount: Int,
    val series: List<Sery>,
    val title: String
)