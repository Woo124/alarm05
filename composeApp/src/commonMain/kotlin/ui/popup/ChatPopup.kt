package ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPopup(
    showPopup: MutableState<Boolean>,
    chatHistory: MutableMap<String, MutableList<String>>,
    chatPopupIndex: MutableState<String>
) {
    val scope = rememberCoroutineScope()

    if (showPopup.value) {
        Dialog(
            onDismissRequest = {
                showPopup.value = false
            }
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
            ) {
                Surface(Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                    Text("Chat: ${chatPopupIndex.value}", fontSize = 24.sp)
                }

                LazyColumn {
                    items(chatHistory[chatPopupIndex.value] ?: emptyList()) { message ->
                        Text(message)
                    }
                }

                //TextField()
            }
        }
    }
}

