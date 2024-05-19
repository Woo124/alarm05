package ui.theme

import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


val dialogOpeningHaptic: suspend CoroutineScope.((HapticFeedbackType) -> Unit, suspend CoroutineScope.() -> Unit) -> Unit
        = { performHapticFeedback, openDial ->
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(30)
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(30)
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(30)
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(30)
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(30)
    performHapticFeedback(HapticFeedbackType.LongPress)
    openDial()
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(100)
    performHapticFeedback(HapticFeedbackType.LongPress)
    delay(50)
    performHapticFeedback(HapticFeedbackType.LongPress)
}
