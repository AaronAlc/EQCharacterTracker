package com.eq.charactertracker.entity;

import com.eq.charactertracker.base.BaseQuest;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Epic1_5QuestEntity extends BaseQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private QuestItemEntity firstStone;

    private QuestItemEntity secondStone;

    private QuestItemEntity thirdStone;

    private QuestItemEntity fourthStone;

    private QuestItemEntity flawlessUnicornHeart;

    private QuestItemEntity perfectUnicornHeart;

    private QuestItemEntity totalUnicornHeart;

    private QuestItemEntity absurdUnicornHeart;

    private QuestItemEntity eyeOfTheTerrorantula;

    private QuestItemEntity eyeOfTheSpectre;

    private QuestItemEntity eyeOfTheVampire;

    private QuestItemEntity eyeOfTheGhost;

    private QuestItemEntity bloodOfTheDragon;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CharacterEntity character;


}
