package com.jason.littlelives.ui.feature.root

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import com.jason.domain.core.NavTarget
import javax.inject.Inject
import javax.inject.Singleton

data class NavigationRouteData(
    val route: String = "",
    val isPopBackStack: Boolean = false,
    val popUpToRoute: String? = null
)

@Singleton
class AppNavigator @Inject constructor() {
    private val mutableSharedFlow = MutableSharedFlow<NavigationRouteData>(extraBufferCapacity = 1)
    var sharedFlow = mutableSharedFlow.asSharedFlow()

    fun popBackStack() {
        mutableSharedFlow.tryEmit(NavigationRouteData(isPopBackStack = true))
    }

    fun goToHomeScreen() {
        navigateTo(navTarget = NavTarget.HomeScreen)
    }

    fun getRoute(navTarget: NavTarget): String {
        return navTarget.route + getRouteSuffix(navTarget)
    }

    private fun getRouteSuffix(navTarget: NavTarget): String {
        return when (navTarget) {
            else -> ""
        }
    }

    private fun getRouteWithArguments(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>?
    ): String {
        return when (navTarget) {
            else -> navTarget.route
        }
    }

    private fun navigateTo(
        navTarget: NavTarget,
        navArguments: List<NamedNavArgument>? = emptyList(),
        popUpToRoute: String? = null,
    ) {
        mutableSharedFlow.tryEmit(
            NavigationRouteData(
                route = getRouteWithArguments(
                    navTarget,
                    navArguments
                ),
                popUpToRoute = popUpToRoute
            )
        )
    }
}