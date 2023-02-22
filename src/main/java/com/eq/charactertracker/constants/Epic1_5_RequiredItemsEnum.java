package com.eq.charactertracker.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum Epic1_5_RequiredItemsEnum {

    FIRST_STONE(2662L, "1st stone", (short)1, "firstStone"),
    SECOND_STONE(2663L, "2nd stone", (short)1, "secondStone"),
    THIRD_STONE(2664L, "3rd stone", (short)1, "thirdStone"),
    FOURTH_STONE(2666L, "4th stone", (short)1, "fourthStone"),
    ABSURD_UNICORN_HEART(2670L, "Absurd Unicorn Heart", (short)1, "flawlessUnicornHeart"),
    FLAWLESS_UNICORN_HEART(2668L, "Flawless Unicorn Heart", (short)1, "perfectUnicornHeart"),
    PERFECT_UNICORN_HEART(2667L, "Perfect Unicorn Heart", (short)1, "totalUnicornHeart"),
    TOTAL_UNICORN_HEART(2669L, "Total Unicorn Heart", (short)1, "absurdUnicornHeart"),
    EYE_OF_THE_TERRORANTULA(3180L, "Eye of the Terrorantula", (short)1, "eyeOfTheTerrorantula"),
    EYE_OF_THE_SPECTRE(3174L, "Eye of the Spectre", (short)1, "eyeOfTheSpectre"),
    EYE_OF_THE_VAMPIRE(3137L, "Eye of the Vampire", (short)1, "eyeOfTheVampire"),
    EYE_OF_THE_GHOST(3098L, "Eye of the Ghost", (short)1, "eyeOfTheGhost"),
    BLOOD_OF_THE_DRAGON(3098L, "Eye of the Ghost", (short)1, "bloodOfTheDragon");

    private Long externalId;
    private String itemName;
    private Short quantityNeeded;
    private String fieldName;

    private static Map<String, Epic1_5_RequiredItemsEnum> mapByFieldName = initFieldNameMap();


    Epic1_5_RequiredItemsEnum(Long externalId, String itemName, Short quantityNeeded, String fieldName) {
        this.externalId = externalId;
        this.itemName = itemName;
        this.quantityNeeded = quantityNeeded;
        this.fieldName = fieldName;
    }

    public static Epic1_5_RequiredItemsEnum lookUpByFieldName(String name){
        return mapByFieldName.get(name);
    }

    private static Map<String, Epic1_5_RequiredItemsEnum> initFieldNameMap() {
        return Arrays.stream(Epic1_5_RequiredItemsEnum.values()).collect(Collectors.toUnmodifiableMap(Epic1_5_RequiredItemsEnum::getFieldName, Function.identity()));
    }

}
