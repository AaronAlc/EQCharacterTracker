package com.eq.charactertracker.constants;

import lombok.Getter;

@Getter
public enum Tier5XegonyKeyQuestItemsEnum {

    MYSTICAL_ESSENCE_OF_DUST(28642L, "Mystical Essence of Dust", (short)1),
    MYSTICAL_ESSENCE_OF_MIST(28641L, "Mystical Essence of Mist", (short)1),
    MYSTICAL_ESSENCE_OF_SMOKE(28640L, "Mystical Essence of Smoke", (short)1),
    MYSTICAL_ESSENCE_OF_WIND(28639L, "Mystical Essence of Wind", (short)1);

    private Long externalId;
    private String itemName;
    private Short quantityNeeded;

    Tier5XegonyKeyQuestItemsEnum(Long externalId, String itemName, Short quantityNeeded) {
        this.externalId = externalId;
        this.itemName = itemName;
        this.quantityNeeded = quantityNeeded;
    }
}
