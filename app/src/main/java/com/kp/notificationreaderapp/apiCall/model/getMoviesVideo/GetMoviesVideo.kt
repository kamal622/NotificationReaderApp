package com.kp.notificationreaderapp.apiCall.model.getMoviesVideo

import com.kp.notificationreaderapp.apiCall.model.Error

data class GetMoviesVideo(
    val data: Data,
    val movie_count: Int,
    val result: Boolean,
    val error:Error
)