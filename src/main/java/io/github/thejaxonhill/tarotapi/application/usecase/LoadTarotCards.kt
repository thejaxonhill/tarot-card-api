package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase
import io.github.thejaxonhill.tarotapi.domain.TarotCard
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository

@UseCase
class LoadTarotCards(private val tarotCardRepository: TarotCardRepository) {
    fun load(page: Int, size: Int): List<TarotCard> {
        val start = page * size
        return tarotCardRepository.loadAll().subList(start, start + size)
    }
}