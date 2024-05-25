package io.github.thejaxonhill.tarotapi;

import lombok.Data;

@Data
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