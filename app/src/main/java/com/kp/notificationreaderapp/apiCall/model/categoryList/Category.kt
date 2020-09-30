package com.kp.notificationreaderapp.apiCall.model.categoryList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
        val category_id: String,
        val category_name: String,
        val is_new: String,
        val is_youtube: String,
        val language_id: String,
        val playlist_url: String,
        val sort_order: String
)