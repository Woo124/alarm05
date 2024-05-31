package device

import korlibs.audio.format.MP3
import korlibs.audio.sound.AudioData
import korlibs.audio.sound.nativeSoundProvider
import korlibs.audio.sound.readSound
import korlibs.io.Korio
import korlibs.io.file.std.resourcesVfs


suspend fun playAudioFromByteArray(byteArray: ByteArray) {
    playAudioByPlatform(byteArray)

//    Korio {
//        resourcesVfs["tts.mp3"].writeBytes(byteArray)
//        val mp3 = resourcesVfs["tts.mp3"].readSound()
//        mp3.play()
//    }
}

expect suspend fun playAudioByPlatform(byteArray: ByteArray)

suspend fun playAudioCommon(byteArray: ByteArray) {
    val decoded: AudioData? = MP3.decode(byteArray)
    if (decoded != null) {
        val sound = nativeSoundProvider.createSound(decoded)
        println("Playing sound")
        sound.play()
    } else {
        println("Error decoding MP3")
    }
}
