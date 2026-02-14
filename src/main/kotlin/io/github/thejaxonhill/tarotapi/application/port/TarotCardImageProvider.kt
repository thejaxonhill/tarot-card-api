package io.github.thejaxonhill.tarotapi.application.port

interface TarotCardImageProvider {
    fun getImage(filename: String): ByteArray?
}