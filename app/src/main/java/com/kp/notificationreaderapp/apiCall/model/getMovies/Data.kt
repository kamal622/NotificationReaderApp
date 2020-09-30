package com.kp.notificationreaderapp.apiCall.model.getMovies

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("is_new")val is_new: String,
        @SerializedName("movie_id")val movie_id: String,
        @SerializedName("movie_title")val movie_title: String,
        @SerializedName("poster_image_url")val poster_image_url: String,
        @SerializedName("small_image_url")val small_image_url: String
)