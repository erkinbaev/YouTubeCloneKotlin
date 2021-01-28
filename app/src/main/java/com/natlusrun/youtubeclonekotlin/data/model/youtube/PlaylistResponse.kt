package com.natlusrun.youtubeclonekotlin.data.model.youtube

import androidx.lifecycle.MutableLiveData

data class PlaylistResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)