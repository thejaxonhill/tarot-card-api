package io.github.thejaxonhill.tarotapi.infrastructure

import io.github.thejaxonhill.tarotapi.domain.TarotCard
import io.github.thejaxonhill.tarotapi.domain.TarotCardRepository
import org.springframework.stereotype.Component

@Component
class InMemoryTarotCardRepository(private val cards: List<TarotCard>) : TarotCardRepository {
    override fun load(id: Int): TarotCard? = cards.find { it.id == id }

    override fun loadAll(): List<TarotCard> = cards
}

