package io.github.thejaxonhill.tarotapi.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class TarotCardControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void whenGetAllCards_thenOk() throws Exception {
        webTestClient.get().uri("/api/v1/cards").exchange().expectStatus().isOk();
    }

    @Test
    void givenId_whenGetCard_thenOk() throws Exception {
        webTestClient.get().uri("/api/v1/cards/1").exchange().expectStatus().isOk();
    }

    @Test
    void givenMissingId_whenGetCard_thenBadRequest() throws Exception {
        webTestClient.get().uri("/api/v1/cards/100").exchange().expectStatus().isBadRequest();
    }

    @Test
    void givenShortname_whenGetCard_thenOk() throws Exception {
        webTestClient.get().uri("/api/v1/cards/shortName/sw10").exchange().expectStatus().isOk();
    }

    @Test
    void givenMissingShortname_whenGetCard_thenBadRequest() throws Exception {
        webTestClient.get().uri("/api/v1/cards/shortName/dne").exchange().expectStatus().isBadRequest();
    }

    @Test
    void whenDrawCard_thenOk() throws Exception {
        webTestClient.get().uri("/api/v1/cards/drawCard").exchange().expectStatus().isOk();
    }

    @Test
    void whenDrawCards_thenOk() throws Exception {
        webTestClient.get().uri("/api/v1/cards/drawCards").exchange().expectStatus().isOk();
    }

}
