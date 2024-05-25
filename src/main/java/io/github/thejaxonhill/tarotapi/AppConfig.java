package io.github.thejaxonhill.tarotapi;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

    @Value(value = "${app.host}")
    private String host;

    @Bean
    List<TarotCard> tarotCards() throws IOException {
        File cardsDataJson = new ClassPathResource("cards_data.json").getFile();
        ObjectMapper om = new ObjectMapper();
        return om.readValue(cardsDataJson, new TypeReference<List<TarotCard>>() {
        })
                .stream().map(tarotCard -> {
                    String imageFilename = tarotCard.getName()
                            .replace(" ", "")
                            .toLowerCase();
                    String imageLink = String.format("%s/api/v1/cards/image/%s.jpg", host, imageFilename);
                    tarotCard.setImageLink(imageLink);
                    return tarotCard;
                })
                .toList();
    }

}
