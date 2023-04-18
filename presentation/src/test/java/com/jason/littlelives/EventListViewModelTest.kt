package com.jason.littlelives

import com.jason.data.repository.EventRepository
import com.jason.domain.core.AppResult
import com.jason.domain.model.Event
import com.jason.domain.usecase.GetEventListUseCase
import com.jason.littlelives.ui.feature.event.viewModel.EventListViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventListViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var eventRepository: EventRepository
    lateinit var eventListUseCase: GetEventListUseCase
    lateinit var eventListViewModel: EventListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        eventListUseCase = GetEventListUseCase(eventRepository)
        eventListViewModel = EventListViewModel(eventListUseCase)
    }

    @Test
    fun `test with have data`(): Unit = runBlocking {
        launch(Dispatchers.IO) {
            Mockito.`when`(eventRepository.getEventList())
                .thenReturn(
                    flow {
                        emit(
                            AppResult.Success(
                                listOf(
                                    Event("aaa")
                                )
                            )
                        )
                    }
                )
            eventListViewModel.getEventList()
            delay(2000)
            val result = eventListViewModel.eventListViewModelDataState.value.events
            assertEquals(listOf(Event("aaa")), result)
        }
    }

    @Test
    fun `test with empty data`(): Unit = runBlocking {
        launch(Dispatchers.IO) {
            Mockito.`when`(eventRepository.getEventList())
                .thenReturn(
                    flow {
                        emit(
                            AppResult.Success(emptyList())
                        )
                    }
                )
            eventListViewModel.getEventList()
            delay(2000)
            val result = eventListViewModel.eventListViewModelDataState.value.events
            assertEquals(emptyList<Event>(), result)
        }
    }

    @Test
    fun `test with error response`(): Unit = runBlocking {
        launch(Dispatchers.IO) {
            Mockito.`when`(eventRepository.getEventList())
                .thenReturn(
                    flow {
                        emit(
                            AppResult.Failure(message = "403 forbidden")
                        )
                    }
                )
            eventListViewModel.getEventList()
            delay(2000)
            val result = eventListViewModel.eventListViewModelDataState.value.errorMsg
            assertEquals("403 forbidden", result)
        }
    }
}