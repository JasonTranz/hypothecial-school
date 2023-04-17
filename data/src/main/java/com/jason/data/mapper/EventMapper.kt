package com.jason.data.mapper

import com.jason.data.entity.EventResponse
import com.jason.data.util.toMap
import com.jason.domain.model.Event
import org.json.JSONObject

fun EventResponse.toEvent(): Event {
    return Event(
        typeName = this.typeName ?: "",
        eventDate = this.eventDate ?: "",
        eventDescription = this.eventDescription ?: "",
        eventSnapshot = JSONObject(this.eventSnapshot ?: "").toMap(),
        eventType = this.eventType ?: "",
        insertedAt = this.insertedAt ?: ""
    )
}