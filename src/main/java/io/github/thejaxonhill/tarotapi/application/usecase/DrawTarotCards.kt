package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository

@UseCase
class DrawTarotCards(private val tarotCardRepository: TarotCardRepository) {
    fun draw(amountToDraw: Int = 3, alreadyDrawn: List<Int> = emptyList()) = tarotCardRepository
        .loadAll().filter { !alreadyDrawn.contains(it.id) }
        .shuffled().take(amountToDraw)
}