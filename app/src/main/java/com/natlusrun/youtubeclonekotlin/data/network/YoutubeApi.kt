package com.natlusrun.youtubeclonekotlin.data.network

import com.natlusrun.youtubeclonekotlin.data.model.youtube.PlaylistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("youtube/v3/playlists")
    suspend fun fetchPlaylists(
        @Query("part") part:String,
        @Query("channelId") channelId:String,
        @Query("key") key:String,
        @Query("maxResults") maxResults: Int
    ): PlaylistResponse

    @GET("youtube/v3/playlists")
    suspend fun getNextPlaylists(
            @Query("part")part : String,
            @Query("channelId") channelId:String,
            @Query("key") key : String,
            @Query("maxResults") maxResults : Int,
            @Query("pageToken") nextPageToken : String? = null
    ): PlaylistResponse

    @GET("youtube/v3/playlistItems")
    suspend fun getPlayList(
            @Query("part")part : String,
            @Query("playlistId") playlistId:String,
            @Query("key") key : String,
            @Query("maxResults") maxResults : Int
    ) : PlaylistResponse

    @GET("youtube/v3/playlistItems")
    suspend fun getNextPlayList(
            @Query("part")part : String,
            @Query("playlistId") playlistId:String,
            @Query("key") key : String,
            @Query("maxResults") maxResults : Int,
            @Query("pageToken") nextPageToken : String? = null
    ) : PlaylistResponse
}