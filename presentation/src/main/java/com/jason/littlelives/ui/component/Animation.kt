package com.jason.littlelives.ui.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import com.jason.littlelives.common.AppConstant.NavigationAnimationSpec.ANIMATION_FADE_IN_TWEEN_DURATION
import com.jason.littlelives.common.AppConstant.NavigationAnimationSpec.ANIMATION_SIDE_IN_TWEEN_DURATION
import com.jason.littlelives.common.AppConstant.NavigationAnimationSpec.INITIAL_OFFSET_X

// specifies the animation that runs when you navigate() to this destination.
@ExperimentalAnimationApi
fun enterTransition(routeScreens: List<String>): EnterTransition? {
    routeScreens.forEach { _ ->
        return slideInHorizontally(
            initialOffsetX = { INITIAL_OFFSET_X },
            animationSpec = tween(ANIMATION_SIDE_IN_TWEEN_DURATION)
        ) + fadeIn(animationSpec = tween(ANIMATION_FADE_IN_TWEEN_DURATION))
    }
    return null
}

// specifies the animation that runs when you popBackStack() to this destination.
@ExperimentalAnimationApi
fun popEnterTransition(routeScreens: List<String>): EnterTransition? {
    routeScreens.forEach { _ ->
        return slideInHorizontally(
            initialOffsetX = { -INITIAL_OFFSET_X },
            animationSpec = tween(ANIMATION_SIDE_IN_TWEEN_DURATION)
        ) + fadeIn(animationSpec = tween(ANIMATION_FADE_IN_TWEEN_DURATION))
    }
    return null
}