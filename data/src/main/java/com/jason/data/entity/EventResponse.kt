package com.jason.data.entity

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("__typename") val typeName: String? = null,
    @SerializedName("eventDate") val eventDate: String? = null,
    @SerializedName("eventDescription") val eventDescription: String? = null,
    @SerializedName("eventSnapshot") val eventSnapshot: String? = null,
    @SerializedName("eventType") val eventType: String? = null,
    @SerializedName("insertedAt") val insertedAt: String? = null
)

data class EventListResponse(
    @SerializedName("userTimeline") val userTimeline: List<EventResponse>? = null
)