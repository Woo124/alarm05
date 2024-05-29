package network

import com.soywiz.korau.format.MP3
import com.soywiz.korau.format.play
import com.soywiz.korio.stream.AsyncStream
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
    println(client)
    try {
        println("Sending GET request $url")
        println(serverInterface+url)
        result = client.get<T>(serverInterface+url)
        println("Received result: $result")

    } finally {
        client.close()
    }

    return result
}


suspend fun sendChatMessage(to: String, message: String) {
    println(serverInterface)
    val result = sendGetRequest<String>("/chat/$to/$message")
}

suspend fun getChatMessages(user: String): List<String> {
    val result = sendGetRequest<String>("/chat/$user")
    return result?.let { Json.decodeFromString<List<String>>(it) } ?: listOf()
}

suspend fun getTTSSound(lang: String, text: String) {
    val result = sendGetRequest<AsyncStream>("/tts/$lang/$text")

    println("========================")
    println(result)

    println("========================")

    result?.let {
        // MP3로 디코딩하고 재생
        val audioData = MP3.decodeStream(it)?.toData()
        audioData?.let {
            val audioStream = it.toAsyncStream()
            audioStream.playAndWait()
        }
    }
}

