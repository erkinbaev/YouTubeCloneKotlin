package com.natlusrun.youtubeclonekotlin.data.local

import androidx.room.*
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item

@Dao
interface YoutubeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlaylistList(list: MutableList<Item>)

    @Query("SELECT * FROM Item")
    fun getPlaylistList(): MutableList<Item>

//    @Query("DELETE FROM Item")
//    fun deleteAll()
}