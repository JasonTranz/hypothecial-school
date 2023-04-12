package com.jason.littlelives.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
fun getStatusBarHeight(): Dp {
    return WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
}

@Composable
fun getBottomNavigationBarHeight(): Dp {
    val contentPaddings = WindowInsets.navigationBars.asPaddingValues()
    return contentPaddings.calculateBottomPadding()
}