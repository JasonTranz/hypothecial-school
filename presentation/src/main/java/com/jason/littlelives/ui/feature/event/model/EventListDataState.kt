package com.jason.littlelives.ui.feature.event.model

import com.jason.domain.model.CollectionEvent
import com.jason.domain.model.Event

data class EventListDataState(
    val events: List<Event> = emptyList(),
    val collectionEvents: List<CollectionEvent> = emptyList(),
    val loadingState: Boolean = false,
    val errorMsg: String? = null
)