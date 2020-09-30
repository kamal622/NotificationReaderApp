package com.kp.notificationreaderapp.apiCall.model.categoryList

import com.google.gson.annotations.SerializedName
import com.kp.notificationreaderapp.apiCall.model.Error

data class CategoryList(
        val data: Data,
        val result: Boolean,
        val error: Error,
)