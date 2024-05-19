package network

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


const val serverInterface = "http://localhost:8080"
const val myName = "user1"


fun encodeURL(url: String): String {
    val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + "-_.~"
    return url.map { if (it in allowedChars) it else "%${it.code.toString(16).uppercase()}" }.joinToString("")
}


suspend inline fun <reified T> sendGetRequest(url: String): T? {
    val client = HttpClient()

    var result: T? = null
    try {
        result = client.get<T>(encodeURL(serverInterface + url))
        println(result)
    } finally {
        client.close()
    }

    return result
}


suspend fun sendChatMessage(to: String, message: String) {
    val result = sendGetRequest<String>("/chat/$to/$message")
}

suspend fun getChatMessages(user: String): List<String> {
    val result = sendGetRequest<String>("/chat.$user")
    return result?.let { Json.decodeFromString<List<String>>(it) } ?: listOf()
}

suspend fun getTTSSound(lang: String, text: String) {
    val result = sendGetRequest<String>("$serverInterface/tts/$lang/$text")
    // TODO: play sound
}
