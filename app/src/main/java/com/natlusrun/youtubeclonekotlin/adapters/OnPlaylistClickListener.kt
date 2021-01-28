package com.natlusrun.youtubeclonekotlin.adapters

import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item


interface OnPlaylistClickListener {
    fun onItemClick(item: Item)
}