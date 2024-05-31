import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    userName = "Karina"
    //userName = "Winter"

    Window(
        onCloseRequest = ::exitApplication,
        title = "alarm05 - $userName",
    ) {
        App()
    }
}
