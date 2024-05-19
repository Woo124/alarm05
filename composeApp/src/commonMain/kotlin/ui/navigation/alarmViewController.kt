package ui.navigation

import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.button_alarm_add
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.popup.AlarmPopup


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AlarmView() {
    val alarmList = mutableListOf<AlarmInfo>(
        AlarmInfo(mutableStateOf("06:40"), mutableStateOf("목요일"), mutableStateOf(true)),
        AlarmInfo(mutableStateOf("07:00"), mutableStateOf("금요일"), mutableStateOf(true)),
    )

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scope = rememberCoroutineScope()
        val showPopup = mutableStateOf(false)
        val alarmPopupIndex = mutableStateOf(-1)

        val alarmPlusPopupLauncher: () -> Unit = {
            scope.launch {
                showPopup.value = true
            }
        }

        AlarmPopup(showPopup, alarmList, alarmPopupIndex)

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    alarmPopupIndex.value = -1
                    alarmPlusPopupLauncher()
                },
                modifier = Modifier.size(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Image(
                    painterResource(Res.drawable.button_alarm_add),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(alarmList) { index, alarm ->
                alarmListComponent(
                    {
                        alarmPopupIndex.value = index
                        alarmPlusPopupLauncher()
                    },
                    alarm.time, alarm.day, alarm.checked
                )
            }
        }
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
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
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
