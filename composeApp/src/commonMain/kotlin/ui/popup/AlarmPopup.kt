package ui.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.navigation.AlarmInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmPopup(
    showPopup: MutableState<Boolean>,
    alarmList: MutableList<AlarmInfo>,
    alarmPopupIndex: MutableState<Int>
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showPopup.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showPopup.value = false
            },
            sheetState = sheetState
        ) {
            Surface(Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                Text("Set a new Alarm", fontSize = 24.sp)
            }


        }
    }
}
