import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.SUITE_Variable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.main.MainAppView


var userName = "이름 없는 유저"


@Composable
@Preview
fun App() {
    MaterialTheme(
        typography = typography
    ) {
        MainAppView()
    }
}

@OptIn(ExperimentalResourceApi::class)
val suiteFontFamily: FontFamily
    @Composable get() = FontFamily(listOf(Font(Res.font.SUITE_Variable)))


val typography
    @Composable get() = Typography(
        defaultFontFamily = suiteFontFamily
    )