package ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.ClickAnimation
import ui.theme.bounceClick


@Composable
fun ChatPopup(
    chatHistory: MutableMap<String, MutableList<String>>,
    chatPopupIndex: MutableState<String>
) {
    var message by rememberSaveable() { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Surface(Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            reverseLayout = true // 역순으로 레이아웃 설정
        ) {
            items(chatHistory[chatPopupIndex.value] ?: emptyList()) { message ->
                Text(message, color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },


                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp), // 텍스트 필드와 버튼 사이에 간격을 둠
                placeholder = { Text("Type a message...") }
            )

            Button(
                onClick = {
                    if (message.isNotEmpty()) {
                        chatHistory[chatPopupIndex.value]?.add(message) // 메시지를 채팅 기록에 추가
                        message = "" // 메시지 전송 후 텍스트 필드 비우기
                    }
                },
                 // 버튼을 텍스트 필드의 중앙에 맞춤
            ) {
                Text("Send")
            }
        }
    }
}