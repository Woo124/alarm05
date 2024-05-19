package ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ui.popup.ChatPopup
import ui.theme.ClickAnimation
import ui.theme.bounceClick


@Composable
fun ChatView() {
    val chatHistory = mutableMapOf<String, MutableList<String>>(
        "Alice" to mutableListOf("Hello", "How are you?"),
        "Bob" to mutableListOf("Hi", "I'm good, thanks!")
    )

    val scope = rememberCoroutineScope()
    val showPopup = mutableStateOf(false)
    val chatPopupIndex = mutableStateOf("")

    val chatPopupLauncher: () -> Unit = {
        scope.launch {
            showPopup.value = true
        }
    }

    ChatPopup(showPopup, chatHistory, chatPopupIndex)

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(chatHistory.keys.toList()) { _, name ->
            ChatListComponent(name) {
                chatPopupIndex.value = name
                chatPopupLauncher()
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
