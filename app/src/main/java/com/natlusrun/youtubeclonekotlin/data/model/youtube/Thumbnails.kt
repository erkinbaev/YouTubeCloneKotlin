package com.natlusrun.youtubeclonekotlin.data.model.youtube

import androidx.room.TypeConverters
import com.natlusrun.youtubeclonekotlin.utils.Converters

data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    @TypeConverters(Converters::class)
    val medium: Medium,
    val standard: Standard
)