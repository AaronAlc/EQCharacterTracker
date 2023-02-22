package com.eq.charactertracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestItem {
    private Long id;
    private Item item;
    private Boolean inInventory;
}
