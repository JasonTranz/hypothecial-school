package com.jason.littlelives.ui.feature.event.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jason.domain.model.CollectionEvent
import com.jason.domain.model.Event
import com.jason.littlelives.R
import com.jason.littlelives.ui.component.GlideImage
import com.jason.littlelives.ui.component.RegularText
import com.jason.littlelives.ui.component.ResImage
import com.jason.littlelives.ui.component.SemiBoldText
import com.jason.littlelives.ui.feature.event.viewModel.EventListViewModel
import com.jason.littlelives.ui.feature.root.AppNavigator
import com.jason.littlelives.util.MediumSpace
import com.jason.littlelives.util.SmallSpace
import com.jason.littlelives.util.TinySpaceX
import com.jason.littlelives.util.toTimeStamp

@Composable
fun EventListScreen(
    navigator: AppNavigator,
    eventListViewModel: EventListViewModel
) {

    val eventListDataState by remember { eventListViewModel.eventListViewModelDataState }.collectAsState()

    // prevent back action
    BackHandler(enabled = false) { }

    LaunchedEffect(true) {
        if (eventListDataState.events.isEmpty()) {
            eventListViewModel.getEventList()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar()

        LazyColumn {
            items(
                count = eventListDataState.collectionEvents.size,
                itemContent = { index ->
                    CollectionEventItem(collectionEvent = eventListDataState.collectionEvents[index])
                }
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier
) {
    val smallSpace = SmallSpace()

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = SmallSpace())
    ) {
        val (userIcon, btnShareFeedback, btnCopy, divider) = createRefs()

        ResImage(
            resIconId = R.drawable.ic_user,
            modifier = Modifier
                .size(20.dp)
                .constrainAs(userIcon) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start, smallSpace)
                }
        )

        ResImage(
            resIconId = R.drawable.ic_inbox,
            modifier = Modifier
                .size(20.dp)
                .constrainAs(btnCopy) {
                    centerVerticallyTo(parent)
                    end.linkTo(parent.end, smallSpace)
                }
        )

        SemiBoldText(
            content = "Share feedback",
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.Gray),
                    shape = CircleShape
                )
                .padding(horizontal = 8.dp, vertical = 5.dp)
                .constrainAs(btnShareFeedback) {
                    centerVerticallyTo(parent)
                    end.linkTo(btnCopy.start, smallSpace)
                }
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    top.linkTo(parent.bottom, margin = 10.dp)
                },
            thickness = 0.5.dp,
            color = Color.Gray
        )
    }
}

sealed class EventType(
    val value: String,
    val title: String
) {
    object CheckIn : EventType("checkIn", "Check in & out")

    object CheckOut : EventType("checkOut", "Check in & out")

    object Portfolio : EventType("portfolio", "Portfolio")

    object Events : EventType("event", "Events")

    object EveryDayHealth : EventType("everydayHealth", "Health")

    object StoryExported : EventType("story_exported", "Story Exported")

    object StoryPublished : EventType("story_published", "Story Published")
}

@Composable
fun CollectionEventItem(collectionEvent: CollectionEvent) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray.copy(alpha = 0.3f))
                .padding(horizontal = MediumSpace(), vertical = TinySpaceX())
        ) {
            RegularText(
                content = collectionEvent.date
            )
        }

        collectionEvent.events.forEach { event ->
            EventItem(event = event)
        }
    }
}

@Composable
fun EventItem(
    event: Event
) {
    val context = LocalContext.current

    val smallSpace = SmallSpace()
    val titleState = remember { mutableStateOf("") }
    val imageUrlState = remember { mutableStateOf<String?>(null) }
    val pdfUrlState = remember { mutableStateOf<String?>(null) }
    val resIconState = remember { mutableStateOf(R.drawable.ic_check_in) }

    val btnActionResIconState = remember { mutableStateOf<Int?>(null) }
    val btnActionResTitleState = remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(event.eventType) {
        when (event.eventType) {
            EventType.CheckIn.value -> {
                titleState.value = EventType.CheckIn.title
                imageUrlState.value = event.eventSnapshot["checkinThumb"].toString()
                resIconState.value = R.drawable.ic_check_in
            }

            EventType.CheckOut.value -> {
                titleState.value = EventType.CheckOut.title
                imageUrlState.value = event.eventSnapshot["checkinThumb"].toString()
                resIconState.value = R.drawable.ic_check_in
            }

            EventType.EveryDayHealth.value -> {
                titleState.value = EventType.EveryDayHealth.title
                resIconState.value = R.drawable.ic_check_in
            }

            EventType.Portfolio.value -> {
                titleState.value = EventType.Portfolio.title
                imageUrlState.value = event.eventSnapshot["imageUrl"].toString()
                resIconState.value = R.drawable.ic_portfolio
            }

            EventType.Events.value -> {
                titleState.value = EventType.Events.title
                btnActionResIconState.value = R.drawable.ic_calendar
                btnActionResTitleState.value = R.string.add
                resIconState.value = R.drawable.ic_calendar
            }

            EventType.StoryExported.value -> {
                titleState.value = EventType.StoryExported.title
                pdfUrlState.value = event.eventSnapshot["url"].toString()
                btnActionResIconState.value = R.drawable.ic_download
                btnActionResTitleState.value = R.string.download
                resIconState.value = R.drawable.ic_portfolio
            }

            EventType.StoryPublished.value -> {
                titleState.value = EventType.StoryPublished.title
                imageUrlState.value = event.eventSnapshot["story_image"].toString()
                resIconState.value = R.drawable.ic_portfolio
            }
        }
    }

    fun onActionButtonPressed() {
        when(event.eventType) {
            EventType.Events.value -> {
                event.eventSnapshot["eventDate"]?.let { startTime ->
                    addCalendar(
                        context = context,
                        startTime = startTime.toString().toTimeStamp(),
                        title = event.eventSnapshot["eventTitle"].toString()
                    )
                }

            }
            EventType.StoryExported.value -> {
                pdfUrlState.value?.let {
                    downloadPdf(
                        context = context,
                        url = pdfUrlState.value.toString()
                    )
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(smallSpace)
    ) {
        val (icon, title, description, thumb, btnAction) = createRefs()

        ResImage(
            resIconId = resIconState.value,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
            modifier = Modifier
                .size(20.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        SemiBoldText(
            modifier = Modifier
                .padding(start = smallSpace)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(icon.end)
                },
            content = titleState.value.uppercase(),
            color = MaterialTheme.colors.primary
        )

        SemiBoldText(
            content = event.eventDescription,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(
                    anchor = title.bottom,
                    margin = 10.dp
                )
                start.linkTo(
                    anchor = icon.end,
                    margin = 16.dp
                )
                end.linkTo(
                    anchor = thumb.start,
                    margin = 16.dp
                )
                width = Dimension.fillToConstraints
            },
            maxLines = Int.MAX_VALUE
        )

        Box(
            modifier = Modifier.constrainAs(thumb) {
                centerVerticallyTo(parent)
                end.linkTo(parent.end)
            }
        ) {
            imageUrlState.value?.let {
                GlideImage(
                    imageUrl = it,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }

        Box(
            modifier = Modifier.constrainAs(btnAction) {
                top.linkTo(anchor = description.bottom, margin = 10.dp)
                start.linkTo(anchor = description.start)
            }
        ) {
            btnActionResTitleState.value?.let {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10))
                        .background(color = MaterialTheme.colors.primary)
                        .padding(horizontal = 7.dp, vertical = 5.dp)
                        .clickable { onActionButtonPressed() },
                    horizontalArrangement = Arrangement.Center
                ) {
                    RegularText(
                        content = stringResource(id = it),
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.padding(end = 7.dp)
                    )

                    btnActionResIconState.value?.let { resIcon ->
                        ResImage(
                            resIconId = resIcon,
                            modifier = Modifier.size(16.dp),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.background)
                        )
                    }
                }
            }
        }
    }

    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 0.5.dp,
        color = Color.Gray
    )
}

fun addCalendar(
    context: Context,
    startTime: Long,
    title: String = "",
    description: String = ""
) {
    val i = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
        .putExtra(CalendarContract.Events.TITLE, title)
        .putExtra(CalendarContract.Events.DESCRIPTION, description)
//        .putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;COUNT=1")
    context.startActivity(i)
}

fun downloadPdf(
    context: Context,
    url: String
) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    context.startActivity(i)
}