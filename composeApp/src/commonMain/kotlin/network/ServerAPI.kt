package network


import device.playAudioFromByteArray
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


const val serverInterface = "http://localhost:8080"


suspend inline fun <reified T> sendGetRequest(url: String): T? {
    val client = HttpClient()

    var result: T? = null
    try {
        println("Sending GET request $url")
        println(serverInterface + url)
        result = client.get<T>(serverInterface + url)
        println("Received result: $result")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        client.close()
    }

    return result
}

suspend fun getChatMessages(user: String): List<String> {
    val result = sendGetRequest<String>("/chat/$user")
    return result?.let { Json.decodeFromString<List<String>>(it) } ?: listOf()
}

suspend fun sendChatMessage(to: String, message: String) {
    val result = sendGetRequest<String>("/chat/$to/$message")
    println(result)
}

suspend fun getTTSSound(lang: String, text: String) {
    val byteArray = sendGetRequest<ByteArray>("/tts/$lang/$text")
    if (byteArray is ByteArray) {
        println("========================")
        println(byteArray.size)
        println("========================")

        playAudioFromByteArray(byteArray)
    }
}
