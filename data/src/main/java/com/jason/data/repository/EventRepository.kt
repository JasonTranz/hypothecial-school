package com.jason.data.repository

import com.jason.data.mapper.toEvent
import com.jason.data.service.EventService
import com.jason.domain.core.AppResult
import com.jason.domain.model.Event
import com.jason.domain.repository.IEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val service: EventService
): IEventRepository {
    override fun getEventList(): Flow<AppResult<List<Event>>> {
        return flow {
            val eventListResponse = service.getEventList()

            if (eventListResponse.userTimeline != null) {
                emit(AppResult.Success(eventListResponse.userTimeline.map { it.toEvent() }))
            } else {
                emit(AppResult.Failure(Exception("null data")))
            }
        }
    }
}