package ui.main

import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.alarm_24dp_fill1_wght700_grad_25_opsz24
import alarm05.composeapp.generated.resources.sms_24dp_fill1_wght700_grad_25_opsz24
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.navigation.AlarmView
import ui.navigation.ChatView
import ui.theme.ClickAnimation
import ui.theme.bounceClick


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainAppView() {
    var showAlarmPage by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Surface(modifier = Modifier, color = Color(31, 31, 31)) {
                Spacer(modifier = Modifier.statusBarsPadding().fillMaxWidth())
            }

            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color(31, 31, 31),
                contentColor = Color(146, 146, 146),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(
                        painter = painterResource(Res.drawable.alarm_24dp_fill1_wght700_grad_25_opsz24),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp).bounceClick(
                            animation = ClickAnimation(1f, 0.9f),
                            onClick = { showAlarmPage = true }
                        ),
                        colorFilter = ColorFilter.tint(if(showAlarmPage)Color.LightGray else Color.DarkGray)
                    )
                    Text(if(showAlarmPage) "Alarms" else "Chats",
                        color = Color.LightGray, fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Image(
                        painter = painterResource(Res.drawable.sms_24dp_fill1_wght700_grad_25_opsz24),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp).bounceClick(
                            animation = ClickAnimation(1f, 0.9f),
                            onClick = { showAlarmPage = false }
                        ),
                        colorFilter = ColorFilter.tint(if(showAlarmPage)Color.DarkGray else Color.LightGray)
                    )
                }
            }

            AnimatedVisibility(!showAlarmPage) {
                SearchBox()
            }

            AnimatedVisibility(showAlarmPage) {
                AlarmView()
            }
            AnimatedVisibility(!showAlarmPage) {
                ChatView()
            }

            Spacer(modifier = Modifier.navigationBarsPadding().fillMaxWidth())
        }
    }
}


@Composable
fun SearchBox() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(31, 31, 31),
        contentColor = Color(146, 146, 146)
    ) {
        Card(
            modifier = Modifier
                .bounceClick(
                    animation = ClickAnimation(1f, 0.97f),
                    onClick = {}
                )
                .fillMaxWidth()
                .padding(top = 14.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color.LightGray
        ) {
            Text("Search", modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), color = Color.Black)
        }
    }
}
