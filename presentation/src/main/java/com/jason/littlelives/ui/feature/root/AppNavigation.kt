package com.jason.littlelives.ui.feature.root

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jason.domain.core.NavTarget
import com.jason.littlelives.ui.feature.home.view.HomeScreen
import com.jason.littlelives.ui.feature.home.viewModel.HomeViewModel
import com.jason.littlelives.ui.feature.splash.view.SplashScreen
import com.jason.littlelives.ui.feature.splash.viewModel.SplashViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navigator: AppNavigator,
    splashViewModel: SplashViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val navController = rememberAnimatedNavController()

    LaunchedEffect(true) {
        navigator.sharedFlow.onEach { routeData ->
            if (routeData.isPopBackStack) {
                if (routeData.route.isEmpty()) {
                    navController.popBackStack()
                } else {
                    navController.popBackStack(route = routeData.route, inclusive = false)
                }
            } else {
                navController.navigate(routeData.route) {
                    routeData.popUpToRoute?.let { popUpTo(it) }
                }
            }
        }.launchIn(this)
    }

    val splashRoute = navigator.getRoute(NavTarget.SplashScreen)
    val homeRoute = navigator.getRoute(NavTarget.HomeScreen)

    AnimatedNavHost(
        navController = navController,
        startDestination = splashRoute
    ) {
        composable(
            route = splashRoute
        ) {
            SplashScreen(
                navigator = navigator,
                splashViewModel = splashViewModel
            )
        }

        composable(
            route = homeRoute
        ) {
            HomeScreen(
                navigator = navigator,
                homeViewModel = homeViewModel
            )
        }
    }
}