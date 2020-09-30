package com.kp.notificationreaderapp.apiCall.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kp.notificationreaderapp.R
import com.kp.notificationreaderapp.apiCall.api.NetworkLayer
import com.kp.notificationreaderapp.apiCall.model.getMoviesVideo.Video
import com.kp.notificationreaderapp.databinding.ActivityVideoListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoListActivity : AppCompatActivity() {

    lateinit var binding : ActivityVideoListBinding
    lateinit var videoListViewModel: VideoListViewModel

    lateinit var responce:String
    lateinit var videoList: List<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_video_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_list)
        videoListViewModel = ViewModelProvider(this).get(VideoListViewModel::class.java)

        val rvVideoList:RecyclerView = findViewById(R.id.rvVideoList)

        val networkLayer = NetworkLayer()

        CoroutineScope(Dispatchers.IO).launch {

            //
            /*var params : HashMap<String, String> = HashMap()
            params.put("language_id", "1")
            params.put("platform", "android")
            params.put("v", "4.7")
            params.put("appid", "mobile.indiatvshowz")
            val response = networkLayer.getCalList(params)*/

            //
            /*var params : HashMap<String, String> = HashMap()
            params.put("language_id", "1")
            params.put("platform", "android")
            params.put("v", "4.7")
            params.put("appid", "mobile.indiatvshowz")
            params.put("start-index", "1")
            params.put("max-results", "10")
            val response = networkLayer.getMovieData(params)*/

            var params : HashMap<String, String> = HashMap()
            params.put("language_id", "1")
            params.put("platform", "android")
            params.put("v", "4.7")
            params.put("appid", "mobile.indiatvshowz")
            params.put("movie_id", "711")
            val response = networkLayer.getMoviesVideo(params)

            //type=song&language_id=1&alt=json&start-index=1&max-results=31
            /*var params : HashMap<String, String> = HashMap()
            params.put("type","song")
            params.put("language_id","1")
            params.put("alt","json")
            params.put("start-index","1")
            params.put("max-results","31")
            val response = networkLayer.getVideos(params)*/

            if (response.result){
                Log.d("resultt : ",response.data.toString())
                responce = response.data.toString()
                videoList = response.data.videos
            }
            else{
                Log.d("resultt : ",response.error.toString())
                responce = response.error.info
            }

        }

    }

}