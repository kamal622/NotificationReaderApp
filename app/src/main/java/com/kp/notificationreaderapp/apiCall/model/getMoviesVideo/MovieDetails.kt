package com.kp.notificationreaderapp.apiCall.model.getMoviesVideo

data class MovieDetails(
    val cast: String,
    val celebs: List<Celeb>,
    val director: String,
    val genre: String,
    val is_new: String,
    val is_youtube: String,
    val movie_id: String,
    val movie_title: String,
    val music: String,
    val photos: List<Photo>,
    val poster_image_url: String,
    val producer: String,
    val rating: String,
    val release_date: String,
    val small_image_url: String,
    val sort_order: String,
    val story_line: String,
    val writer: String
)