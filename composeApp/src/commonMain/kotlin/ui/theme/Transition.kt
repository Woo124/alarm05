package ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable


@Composable
fun fadeInSlideInHorizontally(duration: Int = 400, enterDelay: Int = 0, toLeftDirection: Boolean = true): EnterTransition = fadeIn(
    animationSpec = tween(durationMillis = duration, easing = LinearOutSlowInEasing, delayMillis = enterDelay)
) + slideInHorizontally(
    initialOffsetX = { fullWidth -> if (toLeftDirection) fullWidth else -fullWidth },
    animationSpec = tween(durationMillis = duration, easing = LinearOutSlowInEasing, delayMillis = enterDelay)
)


@Composable
fun fadeOutSlideOutHorizontally(duration: Int = 400, exitDelay: Int = 0, toLeftDirection: Boolean = true): ExitTransition = fadeOut(
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing, delayMillis = exitDelay)
) + slideOutHorizontally(
    targetOffsetX = { fullWidth -> if (toLeftDirection) fullWidth else -fullWidth },
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing, delayMillis = exitDelay)
)


@Composable
fun fadeInSlideInVertically(duration: Int = 400, enterDelay: Int = 0, upwardDirection: Boolean = true): EnterTransition = fadeIn(
    animationSpec = tween(durationMillis = duration, easing = LinearOutSlowInEasing, delayMillis = enterDelay)
) + slideInVertically(
    initialOffsetY = { fullHeight -> if (upwardDirection) fullHeight else -fullHeight },
    animationSpec = tween(durationMillis = duration, easing = LinearOutSlowInEasing, delayMillis = enterDelay)
)


@Composable
fun fadeOutSlideOutVertically(duration: Int = 400, exitDelay: Int = 0, upwardDirection: Boolean = true): ExitTransition = fadeOut(
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing, delayMillis = exitDelay)
) + slideOutVertically(
    targetOffsetY = { fullHeight -> if (upwardDirection) fullHeight else -fullHeight },
    animationSpec = tween(durationMillis = duration, easing = FastOutLinearInEasing, delayMillis = exitDelay)
)
