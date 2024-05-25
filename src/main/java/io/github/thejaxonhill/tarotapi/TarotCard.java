package io.github.thejaxonhill.tarotapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarotCard {

    private Integer id;

    private String type;

    private String shortName;

    private String name;

    private String value;

    private int intValue;

    private String suit;

    private String upMeaning;

    private String revMeaning;

    private String desc;

    private String imageLink;

}