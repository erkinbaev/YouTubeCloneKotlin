package com.natlusrun.youtubeclonekotlin.data.model.youtube

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.natlusrun.youtubeclonekotlin.data.model.youtube.ContentDetails
import com.natlusrun.youtubeclonekotlin.utils.Converters
import java.io.Serializable


@Entity
data class Item(

        @PrimaryKey
        val id: String,
        @TypeConverters(Converters::class)
        val contentDetails: ContentDetails,
        val etag: String,
        val kind: String,
        @TypeConverters(Converters::class)
        val snippet: Snippet
) : Serializable