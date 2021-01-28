package com.natlusrun.youtubeclonekotlin.ui.local_videos

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import com.natlusrun.youtubeclonekotlin.repository.YoutubeLocalRepository

class SavedViewModel : ViewModel() {

    var localData = MutableLiveData<MutableList<Item>>()
    lateinit var localRepo: YoutubeLocalRepository

    fun initRepository( context: Context) {
        localRepo = YoutubeLocalRepository(context)
    }

    fun getDetailsFromDB() {
        localData.value = localRepo.getPlaylistLocal()
    }
}