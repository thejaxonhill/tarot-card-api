package io.github.thejaxonhill.tarotapi.domain

class TarotCard (
    val id: Int,
    val type: String,
    val shortName: String,
    val name: String,
    val value: String,
    val intValue: Int,
    val suit: String? = null,
    val upMeaning: String,
    val revMeaning: String,
    val desc: String,
)