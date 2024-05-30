package network

import com.soywiz.korau.format.MP3
import com.soywiz.korau.format.play
import com.soywiz.korio.stream.AsyncStream
import com.soywiz.korio.stream.openAsync
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json




const val serverInterface = "http://localhost:8080"
const val myName = "user1"


fun encodeURL(url: String): String {
    val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + "-_.~"
    return url.map { if (it in allowedChars) it else "%${it.code.toString(16).uppercase()}" }.joinToString("")
}

suspend inline fun <reified T> sendGetRequest(url: String): T? {
    val client = HttpClient {

    }

    var result: T? = null
    try {
        println("Sending GET request $url")
        println(serverInterface + url)
        result = client.get(serverInterface + url)
        println("Received result: $result")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        client.close()
    }

    return result
}
suspend fun sendChatMessage(to: String, message: String) {
    println(serverInterface)
    val result = sendGetRequest<String>("/chat/$to/$message")
}



suspend fun getTTSSound(lang: String, text: String) {
    val encodedText = encodeURL(text)
    val byteArray = sendGetRequest<ByteArray>("/tts/$lang/$encodedText")

    println("========================")
    println(byteArray?.size)
    println("========================")

    if (byteArray != null) {
        val asyncStream = byteArray.openAsync()
        println("========================")
        println(asyncStream)
        println("========================")

        // MP3로 디코딩
        val audioData = MP3.decodeStream(asyncStream)?.toData()
        println("========================")
        println(audioData)
        println("========================")
        if (audioData != null) {
            // 오디오 데이터를 재생

                audioData.play()

            }
        }
    }

