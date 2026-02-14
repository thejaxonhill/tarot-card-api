package io.github.thejaxonhill.tarotapi.domain

interface TarotCardRepository {
    fun load(id: Int): TarotCard?
    fun loadAll(): List<TarotCard>
}