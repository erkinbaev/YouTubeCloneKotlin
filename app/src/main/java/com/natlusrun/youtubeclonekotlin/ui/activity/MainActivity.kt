package com.natlusrun.youtubeclonekotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.data.model.VideoItem
import androidx.recyclerview.widget.RecyclerView
import com.natlusrun.youtubeclonekotlin.ui.adapter.MainVideoAdapter

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateVideoList(500)
        findViewById<RecyclerView>(R.id.video_rv).adapter= MainVideoAdapter(exampleList )
        findViewById<RecyclerView>(R.id.video_rv).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.video_rv).setHasFixedSize(true)

    }

    private fun generateVideoList(size: Int): List<VideoItem> {
        val list = ArrayList<VideoItem>()
        for (i in 0 until size) {
            val drawable = when (i % 2) {
                0 -> R.drawable.ic_launcher_background
                1 -> R.drawable.ic_launcher_foreground
                else -> R.drawable.ic_snow
            }
            val item = VideoItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}