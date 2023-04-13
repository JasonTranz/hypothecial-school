package com.jason.domain.usecase

import com.jason.domain.core.AppResult
import com.jason.domain.model.Event
import com.jason.domain.repository.IEventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventListUseCase @Inject constructor(
    private val repository: IEventRepository
): IBaseUseCase<Unit, AppResult<List<Event>>> {
    override suspend fun invoke(input: Unit): Flow<AppResult<List<Event>>> {
        return repository.getEventList()
    }
}