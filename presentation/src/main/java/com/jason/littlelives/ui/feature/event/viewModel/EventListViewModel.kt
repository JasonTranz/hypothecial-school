package com.jason.littlelives.ui.feature.event.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jason.domain.core.AppResult
import com.jason.domain.model.CollectionEvent
import com.jason.domain.model.Event
import com.jason.domain.usecase.GetEventListUseCase
import com.jason.littlelives.ui.feature.event.model.EventListDataState
import com.jason.littlelives.util.toDate
import com.jason.littlelives.util.toTimeStamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventListUseCase: GetEventListUseCase
): ViewModel() {

    private val _eventListViewModelDataState = MutableStateFlow(EventListDataState())
    val eventListViewModelDataState = _eventListViewModelDataState.asStateFlow()

    fun getEventList() {
        viewModelScope.launch {
            getEventListUseCase.invoke(Unit)
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when (response) {
                        is AppResult.Success -> {
                            response.data?.let { events ->
                                _eventListViewModelDataState.update { it.copy(collectionEvents = proceedDataEventToCollection(events)) }
                            }
                        }
                        is AppResult.Failure -> {
                            _eventListViewModelDataState.update { it.copy(errorMsg = it.errorMsg) }
                        }
                    }
                }
        }
    }

    private fun proceedDataEventToCollection(events: List<Event>): List<CollectionEvent> {
        val dates = hashMapOf<String, Long>()
        val collections = mutableListOf<CollectionEvent>()

        for (i in events.indices) {
            val timestamp = events[i].eventDate.toTimeStamp("yyyy-MM-dd'T'hh:mm:ss")
            val date = timestamp.toDate()
            dates[date] = timestamp
            events[i].eventDate = date
        }

        for (i in dates.keys) {
            collections.add(
                CollectionEvent(
                    date = i,
                    events = events.filter { it.eventDate == i }
                )
            )
        }

        return collections
    }
}