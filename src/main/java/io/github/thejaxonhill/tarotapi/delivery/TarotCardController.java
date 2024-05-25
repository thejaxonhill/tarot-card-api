package io.github.thejaxonhill.tarotapi.delivery;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.thejaxonhill.tarotapi.TarotCard;
import io.github.thejaxonhill.tarotapi.usecase.DrawTarotCard;
import io.github.thejaxonhill.tarotapi.usecase.LoadTarotCard;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cards")
public class TarotCardController {

    private final DrawTarotCard drawTarotCard;
    private final LoadTarotCard loadTarotCard;

    @GetMapping
    public Flux<TarotCard> getAllCards() {
        return loadTarotCard.loadAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<TarotCard> getCard(@PathVariable(value = "id") Integer id) {
        return loadTarotCard.load(id);
    }

    @GetMapping(value = "/shortName/{shortName}")
    public Mono<TarotCard> getCardByShortName(@PathVariable(value = "shortName") String shortName) {
        return loadTarotCard.loadByShortName(shortName);
    }

    @GetMapping(value = "/drawCard")
    public Mono<TarotCard> drawCard(
            @RequestParam(value = "alreadyDrawn", required = false) List<Integer> alreadyDrawn) {
        return drawTarotCard.drawCard(alreadyDrawn);
    }

    @GetMapping(value = "/drawCards")
    public Flux<TarotCard> drawCards(
            @RequestParam(value = "amount", required = false) Long amount,
            @RequestParam(value = "alreadyDrawn", required = false) List<Integer> alreadyDrawn) {
        return drawTarotCard.drawCards(amount, alreadyDrawn);
    }

    @GetMapping(value = "/image/{filename}")
    public ResponseEntity<InputStreamResource> getImage(
            @PathVariable(value = "filename") String filename) throws IOException {
        Resource imageResource = new ClassPathResource("/cards/" + filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(imageResource.contentLength())
                .body(new InputStreamResource(imageResource.getInputStream()));
    }

}