package com.natlusrun.youtubeclonekotlin.ui.playlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import com.natlusrun.youtubeclonekotlin.data.model.youtube.PlaylistResponse
import com.natlusrun.youtubeclonekotlin.repository.YoutubeLocalRepository
import com.natlusrun.youtubeclonekotlin.repository.YoutubeRepository
import com.natlusrun.youtubeclonekotlin.utils.Resource

class PlaylistViewModel : ViewModel() {
    var repo = YoutubeRepository()
    var localData = MutableLiveData<MutableList<Item>>()
    lateinit var localRepo: YoutubeLocalRepository

    fun initRepository( context: Context) {
        localRepo = YoutubeLocalRepository(context)
    }

    fun addDetailsToDB(list: MutableList<Item>) {
        localRepo.addPlaylistLocal(list)
    }

    fun fetchDetailsListFromServer(videoId: String): LiveData<Resource<PlaylistResponse>> {
        return repo.fetchPlayList(videoId)
    }

    fun fetchNextDetailsList(videoId: String, nextPageToken: String): LiveData<Resource<PlaylistResponse>> {
        return repo.fetchNextPlayList(videoId,nextPageToken)
    }

//    fun deleteAll() {
//        localRepo.deleteAll()
//    }
}