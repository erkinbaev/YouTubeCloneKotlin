package com.natlusrun.youtubeclonekotlin.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.natlusrun.youtubeclonekotlin.data.model.youtube.PlaylistResponse
import com.natlusrun.youtubeclonekotlin.repository.YoutubeRepository
import com.natlusrun.youtubeclonekotlin.utils.Resource

class PlaylistsViewModel : ViewModel() {
    var repo = YoutubeRepository()

    fun fetchPlaylistFromServer(): LiveData<Resource<PlaylistResponse>> {
        return repo.fetchPlaylists()
    }

    fun fetchNextPlaylist(nextPageToken: String): LiveData<Resource<PlaylistResponse>>{
        return repo.fetchNextPlaylists(nextPageToken)
    }
}