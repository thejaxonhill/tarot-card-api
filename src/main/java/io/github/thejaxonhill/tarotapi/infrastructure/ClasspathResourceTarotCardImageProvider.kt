package io.github.thejaxonhill.tarotapi.infrastructure

import io.github.thejaxonhill.tarotapi.application.port.TarotCardImageProvider
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class ClasspathResourceTarotCardImageProvider : TarotCardImageProvider {
    override fun getImage(filename: String): ByteArray? = runCatching {
        ClassPathResource("/cards/$filename.jpg")
    }.getOrNull()?.contentAsByteArray
}