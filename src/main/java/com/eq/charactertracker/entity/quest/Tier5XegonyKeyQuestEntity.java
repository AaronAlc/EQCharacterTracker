package com.eq.charactertracker.entity.quest;

import com.eq.charactertracker.base.BaseTier5Quest;
import com.eq.charactertracker.entity.CharacterEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tier_5_xegony_key_quest")
public class Tier5XegonyKeyQuestEntity extends BaseTier5Quest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity mysticalEssenceOfDust;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity mysticalEssenceOfMist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity mysticalEssenceOfSmoke;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity mysticalEssenceOfWind;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CharacterEntity character;
}
