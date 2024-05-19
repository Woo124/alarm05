package ui.theme

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).setStatusBarTransparent(view, false)
        }
    }

    content()
}

fun Activity.setStatusBarTransparent(localView: View, lightSystemBar: Boolean) {
    window.apply {
        setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    if (Build.VERSION.SDK_INT >= 30) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    WindowCompat.getInsetsController(this.window, localView).isAppearanceLightStatusBars = lightSystemBar
    WindowCompat.getInsetsController(this.window, localView).isAppearanceLightNavigationBars = lightSystemBar
}
