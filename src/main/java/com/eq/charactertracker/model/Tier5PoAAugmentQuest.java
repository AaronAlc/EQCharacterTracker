package com.eq.charactertracker.model;

import lombok.Data;

@Data
public class Tier5PoAAugmentQuest {

    private Long id;
    private QuestItem questItem;
    private QuestItem windParchment;
    private QuestItem containerOfMist;
    private Character character;

}
