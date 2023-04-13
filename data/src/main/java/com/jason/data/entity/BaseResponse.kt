package com.jason.data.entity

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T?,
    @SerializedName("statusCode") val statusCode: Int?,
)