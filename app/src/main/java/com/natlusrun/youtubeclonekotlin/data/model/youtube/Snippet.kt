package com.natlusrun.youtubeclonekotlin.data.model.youtube

import androidx.room.TypeConverters
import com.natlusrun.youtubeclonekotlin.utils.Converters

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    @TypeConverters(Converters::class)
    val thumbnails: Thumbnails,
    val title: String
)