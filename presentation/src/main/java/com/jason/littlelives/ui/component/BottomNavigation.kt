package com.jason.littlelives.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import com.jason.littlelives.R
import com.jason.littlelives.util.DefaultButtonHeight
import com.jason.littlelives.util.getBottomNavigationBarHeight

sealed class BottomNavItem(
    var resIdTitle: Int,
    var icon: Int,
    var route: String
) {
    object News : BottomNavItem(
        R.string.navigation_bar_new_title,
        R.drawable.ic_news,
        "news"
    )

    object CheckIn : BottomNavItem(
        R.string.navigation_bar_check_in_title,
        R.drawable.ic_check_in,
        "checkIn"
    )

    object Inbox : BottomNavItem(
        R.string.navigation_bar_inbox_title,
        R.drawable.ic_inbox,
        "inbox"
    )

    object Portfolio : BottomNavItem(
        R.string.navigation_bar_portfolio_title,
        R.drawable.ic_portfolio,
        "portfolio"
    )

    object More : BottomNavItem(
        R.string.navigation_bar_portfolio_more,
        R.drawable.ic_menu,
        "more"
    )
}

@ExperimentalPagerApi
@Composable
fun CustomBottomNavigation(
    items: List<BottomNavItem>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    Column {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary),
            thickness = 0.5.dp
        )
        BottomNavigation(
            contentColor = Color.Black,
            backgroundColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .clip(
                    RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp
                    )
                )
                .height(DefaultButtonHeight() + getBottomNavigationBarHeight())
        ) {
            items.forEachIndexed { index, item ->
                val selected = index == pagerState.currentPage
                BottomNavigationItem(
                    modifier = Modifier.navigationBarsPadding(),
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.resIdTitle),
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        BoldText(
                            content = stringResource(id = item.resIdTitle),
                            fontSize = 10.sp,
                            color = if (selected) {
                                MaterialTheme.colorScheme.onBackground
                            } else {
                                MaterialTheme.colorScheme.secondary
                            }
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.onBackground,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    alwaysShowLabel = true,
                    selected = selected,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}