package ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.popup.ChatPopup
import ui.theme.ClickAnimation
import ui.theme.bounceClick


@Composable
fun ChatView() {
    var showChatRoom by rememberSaveable { mutableStateOf(false) }
    val chatPopupIndex = mutableStateOf("")

    val chatHistory = mutableMapOf<String, MutableList<String>>(
        "Alice" to mutableListOf("Hello", "How are you?"),
        "Bob" to mutableListOf("Hi", "I'm good, thanks!")
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
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
                        showChatRoom = true
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
                Text("<", modifier = Modifier.bounceClick(
                    onClick = {
                        showChatRoom = false
                    },
                    animation = ClickAnimation(1f, 0.9f)
                ))

                ChatPopup(chatHistory, chatPopupIndex)
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
                animation = ClickAnimation(1f, 0.9f)
            )
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ) {
        Text(text = name, fontSize = 20.sp)
    }
}
