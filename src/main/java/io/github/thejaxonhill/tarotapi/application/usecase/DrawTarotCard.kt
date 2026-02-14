package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase
import io.github.thejaxonhill.tarotapi.domain.TarotCard
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository

@UseCase
class DrawTarotCard(private val tarotCardRepository: TarotCardRepository) {
    fun draw(alreadyDrawnIds: List<Int> = emptyList()): TarotCard = tarotCardRepository.loadAll()
            .filter { !alreadyDrawnIds.contains(it.id) }
            .randomOrNull() ?: throw IllegalStateException("No cards left to draw")
}
