package com.natlusrun.youtubeclonekotlin.repository


import android.content.Context
import androidx.lifecycle.liveData
import com.natlusrun.youtubeclonekotlin.data.local.YoutubeDatabase
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import com.natlusrun.youtubeclonekotlin.data.network.RetrofitClient
import com.natlusrun.youtubeclonekotlin.utils.Resource
import kotlinx.coroutines.Dispatchers

class YoutubeRepository {

    val channelId = "UC-bXlo2JKw9pjM0V0wmGrGA"
    val key  = "AIzaSyAuw9jO7JUEYEeeQ-0GhUelZtJB6IoH_og"
    val part = "snippet,contentDetails"
    val maxResults = 10
    private val api = RetrofitClient().retrofitInstance()

    fun fetchPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchPlaylists(part,channelId,key,maxResults)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchNextPlaylists(nextPageToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextPlaylists(part, channelId, key, maxResults, nextPageToken)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchPlayList(videoListId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPlayList(part, videoListId, key, maxResults)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }

    fun fetchNextPlayList(videoListId: String, nextPageToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextPlayList(part,videoListId,key,maxResults,nextPageToken)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message.toString()))
        }
    }


}
class YoutubeLocalRepository(context: Context) {
    private val youtubeDao = YoutubeDatabase.getDatabase(context).youTubedao()


    fun getPlaylistLocal(): MutableList<Item> {
        return youtubeDao.getPlaylistList()
    }

    fun addPlaylistLocal(list: MutableList<Item>){
        return youtubeDao.addPlaylistList(list)
    }

//    fun deleteAll() {
//        youtubeDao.deleteAll()
//    }
}