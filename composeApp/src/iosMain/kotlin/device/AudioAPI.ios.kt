package device


actual suspend fun playAudioByPlatform(byteArray: ByteArray) {
    playAudioCommon(byteArray)
}
