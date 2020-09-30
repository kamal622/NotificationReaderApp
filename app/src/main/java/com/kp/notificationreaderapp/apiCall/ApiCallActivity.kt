package com.kp.notificationreaderapp.apiCall

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kp.notificationreaderapp.R
import com.kp.notificationreaderapp.apiCall.adapter.ListAdapter
import com.kp.notificationreaderapp.apiCall.api.NetworkLayer
import com.kp.notificationreaderapp.apiCall.model.getMoviesVideo.Video
import com.kp.notificationreaderapp.apiCall.model.getVideos.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiCallActivity : AppCompatActivity() {

    lateinit var textt : TextView
    lateinit var btShowResult : Button
    lateinit var rvList:RecyclerView

    lateinit var responce:String

    lateinit var videoList: List<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_call)

        textt = findViewById(R.id.tv)
        btShowResult = findViewById(R.id.btShowResult)
        rvList = findViewById(R.id.rvList)

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

        btShowResult.setOnClickListener {
            rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            rvList.adapter = ListAdapter(this,videoList,R.layout.video_list)
        }

    }

}