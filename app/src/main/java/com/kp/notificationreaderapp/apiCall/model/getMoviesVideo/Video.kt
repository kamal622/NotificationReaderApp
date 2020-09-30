package com.kp.notificationreaderapp.apiCall.model.getMoviesVideo

data class Video(
    val duration: String,
    val first_released: String,
    val language_id: String,
    val parent_id: String,
    val player_code: String,
    val related: String,
    val sort_order: String,
    val thumb_url: String,
    val type: String,
    val uploaded: String,
    val video_id: String,
    val video_owner: String,
    val video_title: String,
    val view_count: String,
    val youtube_id: String
)