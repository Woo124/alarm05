package ui.popup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
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

    var time by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }

    if (showPopup.value) {
        DisposableEffect(Unit) {
            onDispose {
                time = ""
                day = ""
            }
        }

        ModalBottomSheet(
            onDismissRequest = {
                showPopup.value = false
            },
            sheetState = sheetState
        ) {
            Surface(Modifier.padding(20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("알람 설정", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

                    TextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Time (HH:mm)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                    )

                    TextField(
                        value = day,
                        onValueChange = { day = it },
                        label = { Text("Day") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                    )

                    Button(
                        onClick = {
                            val newAlarm = AlarmInfo(mutableStateOf(time), mutableStateOf(day), mutableStateOf(true))
                            if (alarmPopupIndex.value >= 0) {
                                alarmList[alarmPopupIndex.value] = newAlarm
                            } else {
                                alarmList.add(newAlarm)
                            }
                            showPopup.value = false
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Set Alarm")
                    }
                }
            }
        }
    }
}
