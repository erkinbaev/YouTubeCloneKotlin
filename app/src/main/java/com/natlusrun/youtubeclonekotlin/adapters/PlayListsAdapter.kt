package com.natlusrun.youtubeclonekotlin.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natlusrun.youtubeclonekotlin.R
import com.natlusrun.youtubeclonekotlin.data.model.youtube.Item
import kotlinx.android.synthetic.main.playlists_holder.view.*

class PlayListsAdapter(var onClickListener: OnPlaylistClickListener) :
    RecyclerView.Adapter<PlayListsAdapter.ViewHolder>() {

    var list = ArrayList<Item>()

    fun add(data: List<Item>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.playlists_holder, parent, false)
        return ViewHolder(v)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onColor()
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(list[position])
        }

    }

    override fun getItemCount() = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {

        @SuppressLint("ResourceAsColor")
        fun onColor() {
            itemView.setBackgroundColor(R.color.white)
        }

        fun onBind(item: Item) {
            itemView.apply {
                tvTitle.text = item.snippet?.title
                tvAmountViews.text = (item.contentDetails?.itemCount.toString() + " video series")
                Glide.with(this).load(item.snippet?.thumbnails?.medium?.url).into(ivVideo)
            }
        }

    }

}