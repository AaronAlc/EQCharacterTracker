package com.eq.charactertracker.entity.quest;

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
@Table(name = "Tier_5_PoA_Augment_Quest")
@Data
public class Tier5PoAAugmentQuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuestItemEntity questItem;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity windParchment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private QuestItemEntity containerOfMist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CharacterEntity character;

}
