package me.cupertank

actual object ResourceLoader {
    actual suspend fun bytes(path: Path): ByteArray =
        javaClass.getResourceAsStream(path.toAbsolutePath())?.readBytes() ?: ByteArray(0)
}
