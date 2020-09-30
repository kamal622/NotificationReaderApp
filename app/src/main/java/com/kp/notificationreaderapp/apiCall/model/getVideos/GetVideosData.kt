package com.kp.notificationreaderapp.apiCall.model.getVideos

import com.kp.notificationreaderapp.apiCall.model.Error

data class GetVideosData(
    val data: List<Data>,
    val result: Boolean,
    val video_count: String,
    val error: Error
)