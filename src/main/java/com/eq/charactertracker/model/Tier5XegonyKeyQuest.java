package com.eq.charactertracker.model;

import com.eq.charactertracker.base.BaseTier5Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tier5XegonyKeyQuest extends BaseTier5Quest {

    private Long id;
    private QuestItem mysticalEssenceOfDust;
    private QuestItem mysticalEssenceOfMist;
    private QuestItem mysticalEssenceOfSmoke;
    private QuestItem mysticalEssenceOfWind;

    private Character character;
}
