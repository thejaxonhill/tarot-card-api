package io.github.thejaxonhill.tarotapi.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.thejaxonhill.tarotapi.TarotCard;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DrawTarotCard {

    private final List<TarotCard> tarotCards;

    public Mono<TarotCard> drawCard(List<Integer> alreadyDrawnIds) {
        return Mono.from(drawCards(1L, alreadyDrawnIds));
    }

    public Flux<TarotCard> drawCards(Long amountToDraw, List<Integer> alreadyDrawnIds) {
        List<TarotCard> deck = new ArrayList<>(tarotCards.stream()
                .filter(card -> alreadyDrawnIds == null || !alreadyDrawnIds.contains(card.getId()))
                .toList());
        Collections.shuffle(deck);

        return Flux.fromStream(deck.stream().limit(amountToDraw != null && amountToDraw > 0 ? amountToDraw : 3L));
    }

}
