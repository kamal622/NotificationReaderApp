package com.kp.notificationreaderapp.apiCall.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.notificationreaderapp.R
import com.kp.notificationreaderapp.apiCall.model.getMoviesVideo.Video
import com.squareup.picasso.Picasso

class ViewHolder(itemView: View,val context: Context) : RecyclerView.ViewHolder(itemView) {

    lateinit var tv: TextView
    lateinit var videoImg: ImageView
    fun bindItems(item: Video) {
        //itemView.tvTitle = item.video_title
        tv = itemView.findViewById(R.id.tvTitle)
        videoImg = itemView.findViewById(R.id.videoImg)
        tv.text  = item.video_title
        Picasso.with(context).load(item.thumb_url).into(videoImg)
    }

}