package ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AlarmView() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(scroll).padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val alarmList = mutableSetOf<AlarmInfo>()

        val testAlarm = AlarmInfo(mutableStateOf("17:41"), mutableStateOf("목요일"), mutableStateOf(true))
        val onClick = {

        }

                /*
        val openLampTypeSelector: () -> Unit = {
            scope.launch {
                dialogOpeningHaptic({
                    haptic.performHapticFeedback(it)
                }) {
                    delay(200)
                    showBottomSheet.value = true
                    delay(100)
                }
            }
        }*/

        alarmListComponent(onClick, testAlarm.time, testAlarm.day, testAlarm.checked)

        /*
        LazyColumn(
        ) {
            for (alarm: alarmList) {
                //alarmListComponent(alarm.time, alarm.day, alarm.checked)
            }
        }*/
    }
}


data class AlarmInfo(
    val time: MutableState<String>,
    val day: MutableState<String>,
    val checked: MutableState<Boolean>
)


@Composable
fun alarmListComponent(
    onClick: () -> Unit,
    setTime: MutableState<String>,
    dayInfo: MutableState<String>,
    isChecked: MutableState<Boolean>
) {
    Button(
        onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15, 15, 15, 15),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(31, 31, 31)),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(setTime.value, fontSize = 24.sp, color = Color.White)
                Text(dayInfo.value, fontSize = 12.sp, color = Color.Gray)
            }
            Switch(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = !isChecked.value }
            )
        }
    }
}
