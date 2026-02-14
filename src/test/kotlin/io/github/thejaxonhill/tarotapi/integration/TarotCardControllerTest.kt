package io.github.thejaxonhill.tarotapi.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class TarotCardControllerTest(
    @Autowired private val mockMvc: MockMvc
) {
    @Test
    fun whenGetAllCards_thenOk() {
        mockMvc.get("/api/v1/cards").andExpect { status { isOk() } }
    }

    @Test
    fun givenId_whenGetCard_thenOk() {
        mockMvc.get("/api/v1/cards/1").andExpect { status { isOk() } }
    }

    @Test
    fun givenMissingId_whenGetCard_thenBadRequest() {
        mockMvc.get("/api/v1/cards/100").andExpect { status { isBadRequest() } }
    }

    @Test
    fun whenDrawCard_thenOk() {
        mockMvc.get("/api/v1/cards/draw-card").andExpect { status { isOk() } }
    }

    @Test
    fun whenDrawCards_thenOk() {
        mockMvc.get("/api/v1/cards/draw-cards").andExpect { status { isOk() } }
    }

    @Test
    fun givenFilename_whenGetImage_thenOk() {
        mockMvc.get("/api/v1/cards/aceofcoins.jpg").andExpect { status { isOk() } }
    }
}
