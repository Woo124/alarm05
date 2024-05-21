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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.main.SearchBox
import ui.popup.ChatPopup
import ui.theme.ClickAnimation
import ui.theme.bounceClick


@Composable
fun ChatView() {
    var showChatRoom by rememberSaveable { mutableStateOf(false) }
    val chatPopupIndex = mutableStateOf("")

    val chatHistory = mutableMapOf<String, MutableList<String>>(
        "Karina" to mutableListOf("Hello", "How are you?"),
        "Winter" to mutableListOf("Hi", "I'm good, thanks!")
    )

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "name",//chatPopupIndex.value,
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text("<-",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.bounceClick(
                            onClick = {
                                showChatRoom = false
                            },
                            animation = ClickAnimation(1f, 0.95f)
                        )
                    )
                }

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