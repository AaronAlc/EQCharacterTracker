package com.eq.charactertracker.model;

import com.eq.charactertracker.constants.EquipSlotEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEquipSlot {
    private Long id;
    private EquipSlotEnum equipSlot;
    private Character character;
    private Armor armor;
    private Augmentation aug1;
    private Augmentation aug2;
}
