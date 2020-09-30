package com.kp.notificationreaderapp.apiCall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.notificationreaderapp.R
import com.kp.notificationreaderapp.apiCall.model.getMoviesVideo.Video
import com.squareup.picasso.Picasso


class ListAdapter(val context: Context,val list: List<Video>, val layout:Int) :
        RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false),parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /*inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv:TextView
        lateinit var videoImg:ImageView
        fun bindItems(item: Video) {
            //itemView.tvTitle = item.video_title
            tv = itemView.findViewById(R.id.tvTitle)
            videoImg = itemView.findViewById(R.id.videoImg)

            tv.text  = item.video_title
            Picasso.with(context).load(item.thumb_url).into(videoImg)
        }
    }*/

}