package com.jason.littlelives.ui.feature.splash.view

import com.jason.littlelives.ui.feature.root.AppNavigator
import com.jason.littlelives.ui.feature.splash.viewModel.SplashViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jason.littlelives.ui.component.GlideImage
import com.jason.littlelives.R

@Composable
fun SplashScreen(
    navigator: AppNavigator,
    splashViewModel: SplashViewModel
) {
    val loadingComplete by remember(splashViewModel) { splashViewModel.loadingComplete }.collectAsState()

    fun goToHomeScreen() {
        navigator.goToHomeScreen()
    }

    LaunchedEffect(loadingComplete) {
        if (loadingComplete) {
            goToHomeScreen()
        }
    }

    BackHandler(false) { }

    BoxWithConstraints {
        val size = maxWidth / 2
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                imageUrl = R.drawable.logo_splash,
                modifier = Modifier.size(size),
            )
        }
    }
}