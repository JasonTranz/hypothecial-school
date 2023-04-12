package com.jason.domain.core

sealed class NavTarget(val route: String) {

    object SplashScreen : NavTarget(route = "SplashScreen")

    object HomeScreen : NavTarget(route = "HomeScreen")

    object NewListScreen : NavTarget(route = "NewListScreen")

    object CheckInScreen : NavTarget(route = "CheckInScreen")

    object InboxScreen : NavTarget(route = "InboxScreen")

    object PortfolioScreen : NavTarget(route = "PortfolioScreen")

    object MoreScreen : NavTarget(route = "MoreScreen")
}