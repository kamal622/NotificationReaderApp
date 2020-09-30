package com.kp.notificationreaderapp.apiCall.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(
        val code: Int,
        val info: String
)