package io.github.thejaxonhill.tarotapi.usecase;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import io.github.thejaxonhill.tarotapi.TarotCard;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LoadTarotCard {

    private final List<TarotCard> tarotCards;

    public Mono<TarotCard> load(Integer id) {
        return filterToMono(c -> c.getId().equals(id));
    }

    public Mono<TarotCard> loadByShortName(String shortName) {
        return filterToMono(c -> c.getShortName().equals(shortName));
    }

    public Flux<TarotCard> loadAll() {
        return Flux.fromIterable(tarotCards);
    }

    public Flux<TarotCard> loadAllById(List<Integer> ids) {
        return filterToFlux(c -> ids.contains(c.getId()));
    }

    public Flux<TarotCard> loadAllByShortName(List<String> shortNames) {
        return filterToFlux(c -> shortNames.contains(c.getShortName()));
    }

    private Flux<TarotCard> filterToFlux(Predicate<TarotCard> predicate) {
        return Flux.fromStream(tarotCards.stream()).filter(predicate);
    }

    private Mono<TarotCard> filterToMono(Predicate<TarotCard> predicate) {
        return tarotCards.stream()
                .filter(predicate)
                .findFirst()
                .map(Mono::just)
                .orElseThrow(() -> new RuntimeException("Unable to find tarot card."));
    }

}
