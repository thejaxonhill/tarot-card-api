package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository

@UseCase
class LoadTarotCard(private val tarotCardRepository: TarotCardRepository) {
    fun load(id: Int) = tarotCardRepository.load(id) ?:
    throw NoSuchElementException("No tarot card with id $id")
}
