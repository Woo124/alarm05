package ui.navigation

import alarm05.composeapp.generated.resources.Res
import alarm05.composeapp.generated.resources.undo_24dp_fill0_wght400_grad0_opsz24
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.main.SearchBox
import ui.popup.ChatMessage
import ui.popup.ChatPopup
import ui.theme.ClickAnimation
import ui.theme.bounceClick

import userName


fun getChatPreset(usr: String): MutableMap<String, List<ChatMessage>> {
    return mutableMapOf(
        "Karina" to listOf(ChatMessage("Karina", "내일 나 좀 깨워줘"), ChatMessage(usr, "내가?")),
        "Winter" to listOf(ChatMessage(usr,"내일 나 좀 깨워줘"), ChatMessage("Winter","내가?")),
        "Giselle" to listOf(ChatMessage("Giselle","바빠?"), ChatMessage(usr,"자는 중....")),
        "Ningning" to listOf(ChatMessage("Ningning","안녕?"), ChatMessage(usr,"ㅋㅋㅋㅋㅋㅋ"))
    )
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ChatView() {
    var showChatRoom by rememberSaveable { mutableStateOf(false) }
    val chatPopupIndex = rememberSaveable { mutableStateOf("") }
    val chatHistoryList = rememberSaveable { mutableStateOf(listOf<ChatMessage>()) }

    val chatHistory = rememberSaveable { getChatPreset(userName).filterKeys { userName != it }.toMutableMap() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AnimatedVisibility(
            visible = !showChatRoom ,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            SearchBox()
        }
        AnimatedVisibility(
            visible = !showChatRoom,
            enter = slideInVertically(),
            exit = slideOutVertically()

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(chatHistory.keys.toList()) { _, name ->
                    ChatListComponent(name) {
                        chatPopupIndex.value = name
                        chatHistoryList.value = chatHistory[name]!!
                        showChatRoom = true
                        println(chatPopupIndex.value)
                    }
                }
            }
        }


        AnimatedVisibility(
            visible = showChatRoom,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = chatPopupIndex.value,
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Image(
                        painter = painterResource(Res.drawable.undo_24dp_fill0_wght400_grad0_opsz24),
                        contentDescription = null,
                        modifier = Modifier.bounceClick(
                            onClick = {
                                showChatRoom = false
                            },
                            animation = ClickAnimation(1f, 0.95f)
                        )
                    )
                }

                ChatPopup(chatHistoryList, chatPopupIndex)
            }
        }
    }
}

@Composable
fun ChatListComponent(name: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .bounceClick(
                onClick = onClick,
                animation = ClickAnimation(1f, 0.95f)
            )
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth()
            .background(color = Color.LightGray)

    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = name, fontSize = 20.sp)
    }
}