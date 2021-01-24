package com.natlusrun.youtubeclonekotlin.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.data.model.VideoItem


class MainVideoAdapter(val videoList : List<VideoItem>)
    : RecyclerView.Adapter<MainVideoAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_item
               , parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = videoList[position]

        holder.videoView.setImageResource(currentItem.videoResource)
        holder.textView1.text = currentItem.titleText
        holder.textView2.text = currentItem.numberText


    }

    override fun getItemCount():Int{
        return videoList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val videoView: ImageView = itemView.findViewById(R.id.video_vv)
            val textView1: TextView = itemView.findViewById(R.id.video_playlist_number_tv)
            val textView2: TextView = itemView.findViewById(R.id.video_playlist_number_tv)





    }


}