package com.kp.notificationreaderapp.apiCall.model.categoryList

import com.google.gson.annotations.SerializedName

data class Data(
        val app_setting: AppSetting,
        val category: List<Category>,
        val featured: List<Featured>,
        val version: String
)