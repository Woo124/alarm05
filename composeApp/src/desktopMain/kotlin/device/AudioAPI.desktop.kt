package device

import javazoom.jl.player.Player
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream


actual suspend fun playAudioByPlatform(byteArray: ByteArray) {
    val fileName = "tts.mp3"
    val file = File(fileName)

    val outputStream = FileOutputStream(file)
    outputStream.write(byteArray)
    outputStream.close()

    try {
        val input: InputStream = FileInputStream(file)
        val player = Player(input)
        player.play()
    } catch (e: Exception) {
        System.err.println("파일을 재생하는 중 문제가 발생했습니다: " + e.message)
    }
}
