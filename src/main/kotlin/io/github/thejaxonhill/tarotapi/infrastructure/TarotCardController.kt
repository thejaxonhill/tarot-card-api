package io.github.thejaxonhill.tarotapi.infrastructure

import io.github.thejaxonhill.tarotapi.AppEnv
import io.github.thejaxonhill.tarotapi.application.usecase.*
import io.github.thejaxonhill.tarotapi.domain.TarotCard
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.util.Locale.getDefault

@RestController
@RequestMapping(value = ["/api/v1/cards"])
class TarotCardController (
    private val appEnv: AppEnv,
    private val drawTarotCard: DrawTarotCard,
    private val drawTarotCards: DrawTarotCards,
    private val loadTarotCard: LoadTarotCard,
    private val loadTarotCards: LoadTarotCards,
    private val loadTarotCardImage: LoadTarotCardImage,
) {
    @GetMapping
    fun getCards(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ): ResponseEntity<Page<TarotCardView>> {
        return loadTarotCards.load(page, size)
            .map { it.toView() }
            .let { Page(it, page, size) }
            .toResponseEntity()
    }

    @GetMapping(value = ["/{id}"])
    fun getCard(@PathVariable id: Int): ResponseEntity<TarotCardView> {
        return loadTarotCard.load(id).toView().toResponseEntity()
    }

    @GetMapping(value = ["/draw-card"])
    fun drawCard(
        @RequestParam(value = "alreadyDrawn", required = false, defaultValue = "") alreadyDrawn: List<Int>
    ): ResponseEntity<TarotCardView> =
        drawTarotCard.draw(alreadyDrawn).toView().toResponseEntity()

    @GetMapping(value = ["/draw-cards"])
    fun drawCards(
        @RequestParam(value = "amount", required = false, defaultValue = "3") amount: Int,
        @RequestParam(value = "alreadyDrawn", required = false, defaultValue = "") alreadyDrawn: List<Int>
    ): ResponseEntity<List<TarotCardView>> =
        drawTarotCards.draw(amount, alreadyDrawn).map { it.toView() }.toResponseEntity()

    @GetMapping(value = ["/{filename}.jpg"])
    fun getImage(@PathVariable filename: String): ResponseEntity<ByteArrayResource> {
        val bytes = loadTarotCardImage.load(filename)
        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .contentLength(bytes.size.toLong())
            .body(ByteArrayResource(bytes))
    }

    fun TarotCard.toView() = TarotCardView(
        id = id,
        type = type,
        shortName = shortName,
        name = name,
        value = value,
        intValue = intValue,
        suit = suit,
        upMeaning = upMeaning,
        revMeaning = revMeaning,
        desc = desc,
        imageUrl = "${appEnv.host}/api/v1/cards/${name.replace(" ", "").lowercase(getDefault())}.jpg",
    )
}

private fun <T : Any> T.toResponseEntity() = ResponseEntity.ok(this)

data class Page<T>(
    val content: List<T>,
    val page: Int?,
    val size: Int?,
)

data class TarotCardView(
    val id: Int? = null,
    val type: String? = null,
    val shortName: String? = null,
    val name: String? = null,
    val value: String? = null,
    val intValue: Int = 0,
    val suit: String? = null,
    val upMeaning: String? = null,
    val revMeaning: String? = null,
    val desc: String? = null,
    val imageUrl: String? = null
)