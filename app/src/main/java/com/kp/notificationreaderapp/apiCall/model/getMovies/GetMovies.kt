package com.kp.notificationreaderapp.apiCall.model.getMovies

import com.google.gson.annotations.SerializedName
import com.kp.notificationreaderapp.apiCall.model.Error

data class GetMovies(
        @SerializedName("data")val data: List<Data>,
        @SerializedName("movie_count")val movie_count: String,
        @SerializedName("result")val result: Boolean,
        @SerializedName("error")val error:Error
)