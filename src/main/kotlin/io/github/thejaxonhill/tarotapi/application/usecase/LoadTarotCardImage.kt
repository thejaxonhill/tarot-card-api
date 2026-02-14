package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.port.TarotCardImageProvider
import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase

@UseCase
class LoadTarotCardImage(private val tarotCardImageProvider: TarotCardImageProvider) {
    fun load(filename: String) = tarotCardImageProvider.getImage(filename) ?:
    throw NoSuchElementException("$filename.jpg not found.")
}