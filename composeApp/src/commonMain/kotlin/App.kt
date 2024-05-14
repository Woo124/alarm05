import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

import ui.main.MainAppView


@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        MainAppView()
    }
}
