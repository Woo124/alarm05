package ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import network.getChatMessages
import network.getTTSSound
import network.sendChatMessage

import userName


@Composable
fun ChatPopup(
    chatHistoryList: MutableState<List<ChatMessage>>,
    chatPopupIndex: MutableState<String>
) {
    var message by rememberSaveable() { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val messagingLoop = rememberCoroutineScope()

    messagingLoop.launch {
        while (true) {
            getChatMessages(userName).forEach { newMessage ->
                val history = chatHistoryList.value.toMutableList()
                history.add(ChatMessage(chatPopupIndex.value, newMessage))
                chatHistoryList.value = history.toList()
                getTTSSound("ko", newMessage)
            }
            delay(500)
        }
    }

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
            reverseLayout = false // 역순으로 레이아웃 설정
        ) {
            itemsIndexed(chatHistoryList.value) { _, chatMessage ->
                val alignment = if (chatMessage.sender == userName) Alignment.TopEnd else Alignment.TopStart
                val backgroundColor = if (chatMessage.sender == userName) Color.Gray else Color.LightGray
                Box(
                    contentAlignment = alignment,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)  // 각 채팅 아이템 사이의 간격 추가
                ) {
                    Box(
                        modifier = Modifier
                            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
                            .padding(8.dp)
                            .align(alignment)
                    ) {
                        Text(
                            text = chatMessage.message,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
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
                        val newChatMessage = ChatMessage(sender = userName, message = message)
                        val history = chatHistoryList.value.toMutableList()
                        history.add(newChatMessage)
                        chatHistoryList.value = history.toList()  // 메시지를 채팅 기록에 추가

                        scope.launch {
                            sendChatMessage(chatPopupIndex.value, message)
                            message = "" // 메시지 전송 후 텍스트 필드 비우기
                        }

                        println("[ME: ${userName}] Send message $message to ${chatPopupIndex.value}")
                    }
                },
                 // 버튼을 텍스트 필드의 중앙에 맞춤
            ) {
                Text("Send")
            }
        }
    }
}


data class ChatMessage(
    val sender: String, // 발신자 이름 또는 ID
    val message: String // 채팅 메시지
)

