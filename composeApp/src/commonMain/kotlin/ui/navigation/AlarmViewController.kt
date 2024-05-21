package ui.navigation

import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.button_alarm_add
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.popup.AlarmPopup
import ui.theme.ClickAnimation
import ui.theme.bounceClick
import ui.theme.dialogOpeningHaptic


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AlarmView() {
    val alarmList = mutableListOf<AlarmInfo>(
        AlarmInfo(mutableStateOf("06:40"), mutableStateOf("목요일"), mutableStateOf(true)),
        AlarmInfo(mutableStateOf("07:00"), mutableStateOf("금요일"), mutableStateOf(true)),
        AlarmInfo(mutableStateOf("09:00"), mutableStateOf("금요일"), mutableStateOf(true)),
        )

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val haptic = LocalHapticFeedback.current

        val scope = rememberCoroutineScope()
        val showPopup = mutableStateOf(false)
        val alarmPopupIndex = mutableStateOf(-1)

        val alarmSettingPopupLauncher: () -> Unit = {
            scope.launch {
                showPopup.value = true
                dialogOpeningHaptic({
                    haptic.performHapticFeedback(it)
                }) {
                    delay(200)
                }
            }
        }

        AlarmPopup(showPopup, alarmList, alarmPopupIndex)

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painterResource(Res.drawable.button_alarm_add),
                modifier = Modifier.size(16.dp).bounceClick(
                    animation = ClickAnimation(1f, 0.8f),
                    onClick = {
                        alarmPopupIndex.value = -1
                        alarmSettingPopupLauncher()
                    }
                ),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.LightGray)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsIndexed(alarmList) { index, alarm ->
                AlarmListComponent(
                    {
                        alarmPopupIndex.value = index
                        alarmSettingPopupLauncher()
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
fun AlarmListComponent(
    onClick: () -> Unit,
    setTime: MutableState<String>,
    dayInfo: MutableState<String>,
    isChecked: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .bounceClick(
                animation = ClickAnimation(1f, 0.95f),
                onClick = onClick
            )
            .clip(RoundedCornerShape(15, 15, 15, 15))
            .fillMaxWidth()
            .background(Color(31, 31, 31)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
            Text(setTime.value, fontSize = 24.sp, color = Color.White)
            Text(dayInfo.value, fontSize = 12.sp, color = Color.Gray)
        }
        Switch(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = !isChecked.value }
        )
    }
}
