package com.eq.charactertracker.constants;

import lombok.Getter;

@Getter
public enum EquipSlotEnum {
    CHARM("slot0"),
    LEFT_EAR("slot1"),
    HEAD("slot2"),
    FACE("slot3"),
    RIGHT_EAR("slot4"),
    NECK("slot5"),
    SHOULDER("slot6"),
    ARMS("slot7"),
    BACK("slot8"),
    LEFT_WRIST("slot9"),
    RIGHT_WRIST("slot10"),
    RANGE("slot11"),
    HANDS("slot12"),
    PRIMARY("slot13"),
    SECONDARY("slot14"),
    LEFT_FINGER("slot15"),
    RIGHT_FINGER("slot16"),
    CHEST("slot17"),
    LEGS("slot18"),
    FEET("slot19"),
    WAIST("slot20");

    private String slotNumber;

    EquipSlotEnum(String slotNumber) {
        this.slotNumber = slotNumber;
    }
}
