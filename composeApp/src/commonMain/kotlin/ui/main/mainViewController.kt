package ui.main

import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.compose_multiplatform
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.navigation.AlarmView
import ui.navigation.ChatView


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
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color(31, 31, 31),
                contentColor = Color(146, 146, 146),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        onClick = { showAlarmPage = true },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                    }
                    Button(
                        onClick = { showAlarmPage = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.elevation(0.dp)
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                    }
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
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            backgroundColor = Color.LightGray
        ) {
            Text("Search")
        }
    }
}
