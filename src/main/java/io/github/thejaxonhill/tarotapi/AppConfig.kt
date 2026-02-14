package io.github.thejaxonhill.tarotapi

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.thejaxonhill.tarotapi.domain.TarotCard
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.IOException

@Configuration
class AppConfig {
    @Bean
    @Throws(IOException::class)
    fun tarotCards(objectMapper: ObjectMapper): List<TarotCard?> {
        val resource = ClassPathResource("cards_data.json")
        resource.inputStream.use { input ->
            return objectMapper.readValue(
                input,
                object : TypeReference<List<TarotCard>>() {}
            )
        }
    }
}
