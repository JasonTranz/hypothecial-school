package com.jason.domain.model

data class Event(
    val typeName: String = "",
    var eventDate: String = "",
    val eventDescription: String = "",
    val eventSnapshot: Map<String, Any?> = mapOf(),
    val eventType: String = "",
    val insertedAt: String = ""
)

data class CollectionEvent(
    val events: List<Event> = emptyList(),
    val date: String = ""
)