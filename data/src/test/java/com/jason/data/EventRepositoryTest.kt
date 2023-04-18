package com.jason.data

import com.jason.data.entity.EventListResponse
import com.jason.data.entity.EventResponse
import com.jason.data.repository.EventRepository
import com.jason.data.service.EventService
import com.jason.domain.core.AppResult
import com.jason.domain.model.Event
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class EventRepositoryTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var eventService: EventService

    @Mock
    lateinit var eventRepository: EventRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun `test with have data`() = runBlocking<Unit> {
        launch(Dispatchers.IO) {
            Mockito.`when`(eventService.getEventList())
                .thenReturn(
                    EventListResponse(userTimeline = listOf(EventResponse("aaa")))
                )

            eventRepository.getEventList()
                .flowOn(Dispatchers.IO)
                .catch { emit(AppResult.Failure(Exception(it.message))) }
                .collect { response ->
                    when (response) {
                        is AppResult.Success -> {
                            val result = response.data
                            assertEquals(listOf(Event("aaa")), result)
                        }
                        is AppResult.Failure -> {

                        }
                    }
                }
        }
    }
}