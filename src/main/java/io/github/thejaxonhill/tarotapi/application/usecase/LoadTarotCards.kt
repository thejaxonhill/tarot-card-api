package io.github.thejaxonhill.tarotapi.application.usecase

import io.github.thejaxonhill.tarotapi.application.stereotype.UseCase
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository

@UseCase
class LoadTarotCards(private val tarotCardRepository: TarotCardRepository) {
    fun load() = tarotCardRepository.loadAll()
}