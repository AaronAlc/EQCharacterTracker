package com.eq.charactertracker.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum Tier5PoAAugmentItemsEnum {

    CASTELLAN_SLEEVE(13308L),
    CASTELLAN_BOOK(13343L),
    STORMRIDER_TOES(13609L),
    PHOENIX_BLOOD(13363L),
    PHOENIX_FEATHER(13503L),
    PHOENIX_RIB(13567L),
    PHOENIX_EYE(13578L),
    PHOENIX_BONE(13604L),
    STORMRIDER_HAND(13629L),
    STORMRIDER_BRAIN(13628L),
    STORMRIDER_EYE(13608L),
    STORMRIDER_WING(13607L),
    CASTELLAN_MASK(13312L),
    PHOENIX_TOOTH(13366L),
    STORMRIDER_HEART(13610L),
    CASTELLAN_SYMBOL(13269L),
    STORMRIDER_FLASK(13606L),
    PHOENIX_SKIN(13362L),
    CASTELLAN_ORB(13273L),
    CASTELLAN_EYE(13307L),
    CASTELLAN_EAR(13274L);

    private final Long externalId;

    private final static Map<Long, Tier5PoAAugmentItemsEnum> map = initMap();

    Tier5PoAAugmentItemsEnum(Long externalId) {
        this.externalId = externalId;
    }

    public static Tier5PoAAugmentItemsEnum lookUpPoAAugmentItemByExternalId(Long externalId){
        return map.get(externalId);
    }

    private static Map<Long, Tier5PoAAugmentItemsEnum> initMap() {
        return Arrays.stream(Tier5PoAAugmentItemsEnum.values()).collect(Collectors.toUnmodifiableMap(Tier5PoAAugmentItemsEnum::getExternalId, Function.identity()));
    }
}
