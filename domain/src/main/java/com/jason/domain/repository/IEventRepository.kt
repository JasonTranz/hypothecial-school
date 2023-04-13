package com.jason.domain.repository

import com.jason.domain.core.AppResult
import com.jason.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface IEventRepository {

    fun getEventList(): Flow<AppResult<List<Event>>>
}